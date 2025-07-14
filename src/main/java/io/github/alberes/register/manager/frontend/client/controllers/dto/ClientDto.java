package io.github.alberes.register.manager.frontend.client.controllers.dto;

public record ClientDto(
        String clientId,
        String clientSecret,
        String redirectURI,
        String scope) {
}
