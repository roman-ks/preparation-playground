package com.mashkario.x509;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Profile("x509")
@EnableMethodSecurity
@Configuration
public class Config {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .anyRequest()
                        .authenticated())
                .x509(authz -> authz
                        .subjectPrincipalRegex("CN=(.*?)(?:,|$)"))
                .userDetailsService(userDetailsService());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if (username.equals("Bob")) {
                return new User(username, "",
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            }
            throw new UsernameNotFoundException("User not found!");
        };
    }

}
