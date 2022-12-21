package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.UserRole;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends GenericRepository<UserRole> {

  List<UserRole> findAllByUserId(Long userId);
}