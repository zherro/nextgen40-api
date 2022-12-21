package com.bettersoft.nextgen4api.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Roles {
	ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN,
    CONFIG,
    CONFIG_ROTA;

    public static List<String> getAdminDefaultRoles(List<String> roles) {
        if(roles.contains(ROLE_ADMIN.name())) {
            return List.of(CONFIG_ROTA.name(), CONFIG.name());
        }
        return new ArrayList<>();
    }
}