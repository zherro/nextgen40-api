package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.rest.payload.response.UserResponse;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import com.bettersoft.nextgen4api.search.model.impl.UserSearch;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.modelmapper.ModelMapper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(	name = "users",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User extends BaseEntity<UserSearch, UserResponse> {

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Transient
	private Set<UserRole> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(	name = "user_routes",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
	private Set<Rota> rotas = new HashSet<>();

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Override
	public String entityName() {
		return "User";
	}

	@Override
	public UserResponse toResponse(ModelMapper mapper) {
		return mapper.map(this, UserResponse.class);
	}

	@Override
	public UserSearch toSearchModel(ModelMapper mapper) {
		return mapper.map(this, UserSearch.class);
	}

	public void addRoute(Rota router) {
		if(Objects.isNull(rotas)) {
			rotas = new HashSet<>();
		}
		rotas.add(router);
	}
}