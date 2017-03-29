package com.company.service;

import com.company.domain.Extension;
import com.company.domain.Loan;
import com.company.domain.error.ApiException;
import com.company.domain.error.RequestError;
import com.company.repository.ClientRepository;
import com.company.repository.ExtensionRepository;
import com.company.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExtensionService {

    @Value("${loan.extension.week.factor}")
    private BigDecimal extensionFactorPerWeek;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ExtensionRepository extensionRepository;

    public List<Extension> getExtensions(Long clientId, Long loanId) {
        getClientLoan(clientId, loanId);
        return extensionRepository.findByLoanId(loanId);
    }

    public Extension createExtension(Long clientId, Long loanId, int days) {
        Loan loan = getClientLoan(clientId, loanId);
        BigDecimal dayFactor = extensionFactorPerWeek.subtract(new BigDecimal(1)).divide(new BigDecimal(7), 9, BigDecimal.ROUND_HALF_UP);
        BigDecimal fee = dayFactor.multiply(new BigDecimal(days)).multiply(loan.getAmount());
        loan.setOutstandingAmount(loan.getOutstandingAmount().add(fee));
        loan.setTermLocalDate(loan.getTermLocalDate().plusDays(days));
        Extension extension = new Extension(loan, fee, days, loan.getTermDate(), dayFactor);
        extensionRepository.save(extension);
        return extension;
    }

    private Loan getClientLoan(Long clientId, Long loanId) {
        Loan loan = loanRepository.findByClientIdAndId(clientId, loanId);
        if (loan == null) {
            throw new ApiException(RequestError.E3);
        }
        return loan;
    }

}

