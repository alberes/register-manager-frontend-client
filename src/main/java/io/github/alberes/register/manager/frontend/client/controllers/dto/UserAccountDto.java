package io.github.alberes.register.manager.frontend.client.controllers.dto;

import java.util.Set;

public record UserAccountDto(
        String id,
        String name,
        String email,
        String password,
        String role,
        Set<String> scopes) {
}
