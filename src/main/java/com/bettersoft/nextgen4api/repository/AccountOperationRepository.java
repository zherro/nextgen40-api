package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.AccountOperation;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface AccountOperationRepository extends GenericRepository<AccountOperation> {

    Set<AccountOperation> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM account_operation r WHERE r.name = :name limit 1", nativeQuery = true)
    Optional<AccountOperation> findByName(String name);

}
