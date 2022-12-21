package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface RotasRepository extends GenericRepository<Rota> {

    @Query(nativeQuery = true,
    value = " SELECT r.* FROM rotas r " +
            " INNER JOIN user_routes ur on ur.route_id = r.id AND ur.user_id = :userId")
    Set<Rota> findAllByUser(Long userId);

    @Query(value = "SELECT * FROM rotas r WHERE r.name = :name limit 1", nativeQuery = true)
    Optional<Rota> findByName(String name);
}
