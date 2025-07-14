package io.github.alberes.register.manager.frontend.client.controllers.dto;

import java.time.LocalDateTime;

public record ClientReportDto(
        String id,
        String clientId,
        String redirectURI,
        String scope,
        LocalDateTime lastModifiedDate,
        LocalDateTime createdDate) {
}
