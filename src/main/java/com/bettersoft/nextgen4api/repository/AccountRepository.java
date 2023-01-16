package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends GenericRepository<Account> {

    Set<Account> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM account r WHERE r.name = :name limit 1", nativeQuery = true)
    Optional<Account> findByName(String name);

}
