package com.bettersoft.nextgen4api.repository;

import java.util.Optional;

import com.bettersoft.nextgen4api.model.Role;
import com.bettersoft.nextgen4api.model.enums.Roles;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role> {
  Optional<Role> findByName(Roles name);
}