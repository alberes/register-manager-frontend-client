package io.github.alberes.register.manager.frontend.client.controllers;

import io.github.alberes.register.manager.frontend.client.constants.Constants;
import io.github.alberes.register.manager.frontend.client.controllers.dto.ClientDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.ClientReportDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.ClientUpdateDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.page.PageReport;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.RegisterManagerException;
import io.github.alberes.register.manager.frontend.client.services.ClientService;
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

@Controller
@RequiredArgsConstructor
public class ClientController extends GenericController{

    private final ClientService service;

    @GetMapping("/clients")
    public String clients(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                        @AuthenticationPrincipal OidcUser oidcUser,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                        Model model){
        PageReport clients = this.service.clients(client.getAccessToken().getTokenValue(),
                    page, linesPerPage, orderBy, direction);
        clients.setOrderBy(orderBy);
        clients.setDirection(direction);
        model.addAttribute(Constants.OIDCUSER, oidcUser);
        model.addAttribute(Constants.CLIENTS, clients);
        return Constants.LIST_CLIENT;
    }

    @GetMapping("/new-client")
    public String newClient(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                          @AuthenticationPrincipal OidcUser oidcUser,
                          Model model){
        Object clientDto = model.getAttribute(Constants.CLIENT);
        if(clientDto == null) {
            model.addAttribute(Constants.CLIENT, new ClientDto("", "", "", ""));
        }
        model.addAttribute(Constants.ROLES, Constants.ROLES_LIS);
        return Constants.NEW_CLIENT;
    }

    @PostMapping("/save-client")
    public String saveClient(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                           @AuthenticationPrincipal OidcUser oidcUser,
                           ClientDto dto, Model model){
        try {
            URI uri = this.service.save(client.getAccessToken().getTokenValue(), dto);
            String id = this.extractId(uri);
            return this.editClient(client, oidcUser, id, model);
        }catch(RegisterManagerException registerManagerException){
            registerManagerException.getStackTrace();
            if(HttpStatus.CONFLICT.value() == registerManagerException.getStandardError().getStatus()){
                model.addAttribute(Constants.REGISTRATION_WITH_E_CLIENT_ID, registerManagerException.getStandardError().getMessage());
            }else{
                this.createMessages(model, registerManagerException.getStandardError());
            }
            model.addAttribute(Constants.CLIENT, dto);
            model.addAttribute(Constants.ROLES, Constants.ROLES_LIS);
            return this.newClient(client, oidcUser, model);
        }
    }

    @GetMapping("/edit-client/{clientId}")
    public String editClient(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                           @AuthenticationPrincipal OidcUser oidcUser,
                           @PathVariable String clientId, Model model){
        ClientReportDto clientDto = this.service.findClient(client.getAccessToken().getTokenValue(), clientId);
        model.addAttribute(Constants.CLIENT, clientDto);
        return Constants.EDIT_CLIENT;
    }

    @PostMapping("/update-client/{clientId}")
    public String updateClient(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                             @AuthenticationPrincipal OidcUser oidcUser,
                             @PathVariable String clientId, ClientUpdateDto dto, Model model){
        this.service.update(client.getAccessToken().getTokenValue(), clientId, dto);
        ClientReportDto clientDto = this.service.findClient(client.getAccessToken().getTokenValue(), clientId);
        model.addAttribute(Constants.CLIENT, clientDto);
        return Constants.EDIT_CLIENT;
    }

    @GetMapping("/delete-client/{clientId}")
    public String deleteClient(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                             @AuthenticationPrincipal OidcUser oidcUser,
                             @PathVariable String clientId, Model model){
        this.service.delete(client.getAccessToken().getTokenValue(), clientId);
        return clients(client, oidcUser, 0, 24, Constants.CLIENT_ID, Constants.ASC, model);
    }
}
