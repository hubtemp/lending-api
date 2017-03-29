package com.company.repository;

import com.company.domain.Client;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, Long> {

    Client findOne(Long id);

}
