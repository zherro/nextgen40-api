package com.bettersoft.nextgen4api.repository;

import com.bettersoft.nextgen4api.model.ContractModel;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;

import java.util.Set;

public interface ContractModelRepository extends GenericRepository<ContractModel> {

    Set<ContractModel> findAllByStatus(Status status);

}
