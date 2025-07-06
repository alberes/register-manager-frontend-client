package io.github.alberes.register.manager.frontend.client.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserAccountController {

    @GetMapping({"/", "/home", "/login"})
    public Mono<String> index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                              @AuthenticationPrincipal OidcUser oidcUser){
        return Mono.just(client.getAccessToken().getTokenValue());
        //return Constants.LOGIN;
    }
}
