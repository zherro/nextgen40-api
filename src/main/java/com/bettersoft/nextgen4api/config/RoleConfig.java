package com.bettersoft.nextgen4api.config;

import com.bettersoft.nextgen4api.model.Role;
import com.bettersoft.nextgen4api.model.enums.Roles;
import com.bettersoft.nextgen4api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class RoleConfig {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void createRules() {

        Arrays.stream(Roles.values()).forEach(role -> {
            var r = roleRepository.findByName(role);
            if(r.isEmpty()) {
                create(role);
            }
        });
    }

    private Role create(Roles role) {
        return roleRepository.save(new Role(role));
    }
}
