package org.leombprojects.order.configs.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {
    private final OAuth2ResourceServerProperties.Jwt properties;

    public ApplicationConfig(OAuth2ResourceServerProperties properties) {
        this.properties = properties.getJwt();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withJwkSetUri(this.properties.getJwkSetUri()).build();
        nimbusJwtDecoder.setJwtValidator(this.jwtValidator());
        return nimbusJwtDecoder;
    }

    private OAuth2TokenValidator<Jwt> jwtValidator(){
        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
        String issuerUri = this.properties.getIssuerUri();
        if(StringUtils.hasText(issuerUri)){
            validators.add(new JwtIssuerValidator(issuerUri));
        }
        validators.add(new JwtTimestampValidator());
        return new DelegatingOAuth2TokenValidator<>(validators);
    }
}
