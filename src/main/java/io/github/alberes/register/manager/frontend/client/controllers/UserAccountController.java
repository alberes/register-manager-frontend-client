package io.github.alberes.register.manager.frontend.client.controllers;

import io.github.alberes.register.manager.frontend.client.constants.Constants;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountReportDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountUpdateDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.page.PageReport;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.RegisterManagerException;
import io.github.alberes.register.manager.frontend.client.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserAccountController extends GenericController{

    private final UserAccountService service;

    @GetMapping({"/", "/home", "/login"})
    public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                              @AuthenticationPrincipal OidcUser oidcUser, Model model){
        model.addAttribute(Constants.OIDCUSER, oidcUser);
        return "home";
    }

    @GetMapping("/users")
    public String users(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                        @AuthenticationPrincipal OidcUser oidcUser,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                        Model model){
        PageReport users = this.service.users(client.getAccessToken().getTokenValue(),
                    page, linesPerPage, orderBy, direction);
        users.setOrderBy(orderBy);
        users.setDirection(direction);
        model.addAttribute(Constants.OIDCUSER, oidcUser);
        model.addAttribute(Constants.USERS, users);
        return Constants.LIST_USER;
    }

    @GetMapping("/new-user")
    public String newUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                          @AuthenticationPrincipal OidcUser oidcUser,
                          Model model){
        Object user = model.getAttribute(Constants.USER);
        if(user == null) {
            model.addAttribute(Constants.USER, new UserAccountDto("", "", "", "", "", Set.of()));
        }
        model.addAttribute(Constants.ROLES, Constants.ROLES_LIS);
        model.addAttribute(Constants.SCOPES_LABEL, Constants.SCOPES_LIST);
        return Constants.NEW_USER;
    }

    @PostMapping("/save-user")
    public String saveUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                           @AuthenticationPrincipal OidcUser oidcUser,
                           UserAccountDto dto, Model model){
        try {
            URI uri = this.service.save(client.getAccessToken().getTokenValue(), dto);
            String id = this.extractId(uri);
            return this.editUser(client, oidcUser, id, model);
        }catch(RegisterManagerException registerManagerException){
            registerManagerException.getStackTrace();
            if(HttpStatus.CONFLICT.value() == registerManagerException.getStandardError().getStatus()){
                model.addAttribute(Constants.USEREMAILCONFLIT, registerManagerException.getStandardError().getMessage());
            }else{
                this.createMessages(model, registerManagerException.getStandardError());
            }
            model.addAttribute(Constants.USER, dto);
            model.addAttribute(Constants.ROLES, Constants.ROLES_LIS);
            model.addAttribute(Constants.SCOPES_LABEL, Constants.SCOPES_LIST);
            return this.newUser(client, oidcUser, model);
        }
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                           @AuthenticationPrincipal OidcUser oidcUser,
                           @PathVariable String id, Model model){
        UserAccountReportDto user = this.service.findUser(client.getAccessToken().getTokenValue(), id);
        model.addAttribute(Constants.USER, user);
        return Constants.EDIT_USER;
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                             @AuthenticationPrincipal OidcUser oidcUser,
                             @PathVariable String id, UserAccountUpdateDto dto, Model model){
        this.service.update(client.getAccessToken().getTokenValue(), id, dto);
        UserAccountReportDto user = this.service.findUser(client.getAccessToken().getTokenValue(), id);
        model.addAttribute(Constants.USER, user);
        return Constants.EDIT_USER;
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                             @AuthenticationPrincipal OidcUser oidcUser,
                             @PathVariable String id, Model model){
        this.service.delete(client.getAccessToken().getTokenValue(), id);
        return users(client, oidcUser, 0, 24, Constants.NAME, Constants.ASC, model);
    }
}
