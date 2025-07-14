package io.github.alberes.register.manager.frontend.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${app.registermanagerresource.url}")
    private String guestURL;

    @Bean
    public WebClient webClient(WebClient.Builder buider){
        return buider.baseUrl(this.guestURL).build();
    }
}