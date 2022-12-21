package com.bettersoft.nextgen4api.service;

import com.bettersoft.nextgen4api.model.Role;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.model.UserRole;
import com.bettersoft.nextgen4api.repository.RotasRepository;
import com.bettersoft.nextgen4api.repository.UserRepository;
import com.bettersoft.nextgen4api.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final RotasRepository rotasRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		Set<Role> roles = Optional.ofNullable(userRoleRepository.findAllByUserId(user.getId()))
				.orElse(new ArrayList<>())
				.stream()
				.map(UserRole::getRole).collect(Collectors.toSet());
		return UserDetailsImpl.build(user, roles);
	}

	public Set<Rota> myRoutes(final Long userId) {
		return rotasRepository.findAllByUser(userId);
	}

}