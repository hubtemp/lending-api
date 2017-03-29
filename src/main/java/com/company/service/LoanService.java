package com.company.service;

import com.company.domain.Client;
import com.company.domain.error.ApiException;
import com.company.domain.error.RequestError;
import com.company.repository.ClientRepository;
import com.company.repository.LoanRepository;
import com.company.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class LoanService {

    @Value("${loan.min.amount}")
    private BigDecimal loanMinAmount;
    @Value("${loan.max.amount}")
    private BigDecimal loanMaxAmount;
    @Value("${loan.min.term}")
    private int loanMinTerm;
    @Value("${loan.max.term}")
    private int loanMaxTerm;
    @Value("${loan.risk.time.from}")
    private String riskTimeFrom;
    @Value("${loan.risk.time.to}")
    private String riskTimeTo;
    @Value("${loan.max.applications.day}")
    private long maxApplicationsPerDay;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Loan> getLoans(Long clientId) {
        return loanRepository.findByClientId(clientId);
    }

    public Loan getLoan(Long clientId, Long loanId) {
        Loan loan = loanRepository.findByClientIdAndId(clientId, loanId);
        if (loan == null) {
            throw new ApiException(RequestError.E3);
        }
        return loan;
    }

    public Loan createLoan(String ipAddress, Long clientId, BigDecimal amount, int term) {
        if (amount.compareTo(loanMinAmount) == -1 || amount.compareTo(loanMaxAmount) == 1 || term < loanMinTerm || term > loanMaxTerm) {
            throw new ApiException(RequestError.E4);
        }
        // simulated loan application risk analysis
        List<Loan> loans = loanRepository.findByClientId(clientId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime fromDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(riskTimeFrom));
        LocalDateTime toDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(riskTimeTo));
        long sameDayApplicationCount = loans.stream().filter(loan -> loan.getIpAddress().equals(ipAddress) && loan.getApplicationLocalDate().compareTo(currentDateTime.toLocalDate()) == 0).count();
        if ((amount.compareTo(loanMaxAmount) == 0 && currentDateTime.compareTo(fromDateTime) == 1 && currentDateTime.compareTo(toDateTime) == -1) || sameDayApplicationCount >= maxApplicationsPerDay) {
            throw new ApiException(RequestError.E5); // for this use case, let's always send back rejection
        }
        Client client = clientRepository.findOne(clientId);
        Loan loan = new Loan(client, term, currentDateTime.toLocalDate(), currentDateTime.toLocalDate().plusDays(term), amount, ipAddress);
        loanRepository.save(loan);
        return loan;
    }

}
