package com.company.repository;

import com.company.domain.Extension;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ExtensionRepository extends Repository<Extension, Long> {

    List<Extension> findByLoanId(Long loanId);

    Extension save(Extension extension);

}
