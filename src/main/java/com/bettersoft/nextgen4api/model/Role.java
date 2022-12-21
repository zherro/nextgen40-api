package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.model.enums.Roles;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(	name = "roles",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "name")
		})
public class Role extends BaseEntity<GenericSearch, GenericResponse> {

	public Role(Roles name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Roles name;

	@Override
	public String entityName() {
		return "Role";
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