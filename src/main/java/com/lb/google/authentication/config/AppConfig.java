package com.lb.google.authentication.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.lb.google.authentication.authentication.GoogleTokenVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    @Bean
    public GoogleTokenVerifier googleTokenVerifier(@Value("${client.id}") String clientId) {
        return new GoogleTokenVerifier(getNetHttpTransport(), getGsonFactory(), clientId);
    }

    @Bean
    public NetHttpTransport getNetHttpTransport() {
        return new NetHttpTransport();
    }

    @Bean
    public GsonFactory getGsonFactory() {
        return new GsonFactory();
    }

}
