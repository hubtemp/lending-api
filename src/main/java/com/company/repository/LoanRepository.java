package com.company.repository;

import com.company.domain.Loan;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LoanRepository extends Repository<Loan, Long> {

    Loan findByClientIdAndId(Long clientId, Long loanId);

    List<Loan> findByClientId(Long clientId);

    Loan save(Loan loan);

    void delete(Loan loan);

}
