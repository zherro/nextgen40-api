package com.bettersoft.nextgen4api.repository;

import java.util.Optional;

import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}