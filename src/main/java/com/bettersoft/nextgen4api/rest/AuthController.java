package com.bettersoft.nextgen4api.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bettersoft.nextgen4api.model.Role;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.model.UserRole;
import com.bettersoft.nextgen4api.model.enums.Roles;
import com.bettersoft.nextgen4api.repository.RoleRepository;
import com.bettersoft.nextgen4api.repository.UserRepository;
import com.bettersoft.nextgen4api.rest.payload.request.LoginRequest;
import com.bettersoft.nextgen4api.rest.payload.request.SignupRequest;
import com.bettersoft.nextgen4api.rest.payload.response.JwtResponse;
import com.bettersoft.nextgen4api.rest.payload.response.MessageResponse;
import com.bettersoft.nextgen4api.security.jwt.JwtUtils;
import com.bettersoft.nextgen4api.service.UserDetailsImpl;
import com.bettersoft.nextgen4api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	public static final String NOT_FOUND_ROLE = "Error: Role is not found.";
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		Set<Rota> routes = userDetailsService.myRoutes(userDetails.getId());
		roles.addAll(Roles.getAdminDefaultRoles(roles));
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles,
												 routes
				));
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Roles.ROLE_USER)
					.orElseThrow(RuntimeException::new);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException(NOT_FOUND_ROLE));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException(NOT_FOUND_ROLE));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(Roles.valueOf(role))
								.orElseThrow(() -> new RuntimeException(NOT_FOUND_ROLE));
						roles.add(userRole);
				}
			});
		}

		var roleList = Optional.ofNullable(roles).orElse(new HashSet<>()).stream()
			.map(role -> new UserRole(user, role))
			.collect(Collectors.toSet());

		user.setRoles(roleList);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}