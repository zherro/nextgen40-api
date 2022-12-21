package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(	name = "user_roles")
public class UserRole extends BaseEntity<GenericSearch,  GenericResponse> {

    public UserRole (final User user, final Role role) {
        this.user = user;
        this.role = role;
        this.createdBy = user;
    }

    public UserRole (final User user, final Role role, final User createdBy) {
        this.user = user;
        this.role = role;
        this.createdBy = createdBy;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name="role_id")
    private Role role;

    @Override
    public String entityName() {
        return "User Role";
    }

    @Override
    public GenericResponse toResponse(ModelMapper mapper) {
        return null;
    }

    @Override
    public GenericSearch toSearchModel(ModelMapper mapper) {
        return null;
    }
}
