package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends GenericRepository<Account> {

    @Query(nativeQuery = true,
    value = " SELECT r.* FROM rotas r " +
            " INNER JOIN user_routes ur on ur.route_id = r.id AND ur.user_id = :userId")
    Set<Rota> findAllByUser(Long userId);

    Set<Rota> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM rotas r WHERE r.name = :name limit 1", nativeQuery = true)
    Optional<Rota> findByName(String name);

}
