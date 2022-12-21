package com.bettersoft.nextgen4api.repository.generic;

import com.bettersoft.nextgen4api.model.BaseEntity;
import com.bettersoft.nextgen4api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    Optional<T> findByUuid(String uuid);
    Optional<T> findById(Long id);
    List<T> findAllByIdIn(List<Long> ids);

    @Query(value = "FROM User u WHERE u.id = :id")
    User getUserById(Long id);
}