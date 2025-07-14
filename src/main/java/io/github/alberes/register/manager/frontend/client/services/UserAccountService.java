package io.github.alberes.register.manager.frontend.client.services;

import io.github.alberes.register.manager.frontend.client.constants.Constants;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountReportDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.UserAccountUpdateDto;
import io.github.alberes.register.manager.frontend.client.controllers.dto.page.PageReport;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.RegisterManagerException;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.StandardErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Controller
@RequiredArgsConstructor
public class UserAccountService {

    private final WebClient webClient;

    @Value("${app.registermanagerresource.user.resource}")
    private String resource;

    @Value("${app.registermanagerresource.user.resourceid}")
    private String resourceid;

    public URI save(String token, UserAccountDto dto){
        return this.webClient
                .post()
                .uri(this.resource)
                .header(HttpHeaders.AUTHORIZATION, Constants.BEARER + token)
                .bodyValue(dto)
                .exchangeToMono(response -> {
                        if(response.statusCode().is2xxSuccessful()){
                            return Mono.justOrEmpty(response.headers().asHttpHeaders().getLocation());
                        }else{
                            return response
                                    .bodyToMono(StandardErrorDto.class)
                                    .flatMap(standardError -> {
                                        return Mono.error(new RegisterManagerException(standardError));
                                    });
                        }
                    })
                .block();
    }

    public HttpStatus update(String token, String id, UserAccountUpdateDto dto){
        Mono<HttpStatusCode> monoHttpStatusCode =
            this.webClient
                .put()
                .uri(this.resourceid, id)
                .header(HttpHeaders.AUTHORIZATION, Constants.BEARER + token)
                .bodyValue(dto)
                .retrieve()
                .onStatus(
        status -> status.isError(),
        resource -> resource.bodyToMono(StandardErrorDto.class)
                        .flatMap(standardError -> Mono.error(new RegisterManagerException(standardError)))
                ).toBodilessEntity()
                .map(ResponseEntity::getStatusCode);

        HttpStatusCode httpStatusCode = monoHttpStatusCode.block();
        if(httpStatusCode.is2xxSuccessful()){
            return HttpStatus.resolve(httpStatusCode.value());
        }else{
            Mono.error(new RuntimeException(Constants.COULD_NOT_UPDATE_RESOURCE + httpStatusCode.value()));
        }
        return null;
    }

    public HttpStatus delete(String token, String id){
        Mono<HttpStatusCode> monoHttpStatusCode =
            this.webClient
                .delete()
                .uri(this.resourceid, id)
                .header(HttpHeaders.AUTHORIZATION, Constants.BEARER + token)
                .retrieve()
                .onStatus(
        status -> status.isError(),
        resource -> resource.bodyToMono(StandardErrorDto.class)
                        .flatMap(standardError -> Mono.error(new RegisterManagerException((standardError))))
                ).toBodilessEntity()
                .map(ResponseEntity::getStatusCode);

        HttpStatusCode httpStatusCode = monoHttpStatusCode.block();
        if(httpStatusCode.is2xxSuccessful()){
            return HttpStatus.resolve(httpStatusCode.value());
        }else{
            Mono.error(new RuntimeException(Constants.COULD_NOT_DELETE_RESOURCE + httpStatusCode.value()));
        }
        return null;
    }

    public UserAccountReportDto findUser(String token, String id){
        return this.webClient
                .get()
                .uri(this.resourceid, id)
                .header(HttpHeaders.AUTHORIZATION, Constants.BEARER + token)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(StandardErrorDto.class)
                                .flatMap(standardError -> Mono.error(new RegisterManagerException(standardError)))

                ).bodyToMono(UserAccountReportDto.class)
                .block();
    }

    public PageReport users(String token, Integer page, Integer linesPerPage,
                            String orderBy, String direction){
        PageReport users = this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(this.resource)
                        .queryParam("page", page)
                        .queryParam("linesPerPage", linesPerPage)
                        .queryParam("orderBy", orderBy)
                        .queryParam("direction", direction)
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, Constants.BEARER + token)
                .retrieve()
                .bodyToMono(PageReport.class)
                .block();
        return users;
    }


}
