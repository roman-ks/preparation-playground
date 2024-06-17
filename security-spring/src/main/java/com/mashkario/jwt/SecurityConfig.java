package com.mashkario.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Profile("jwt")
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;

//    @Value("${spring.security.user.name}")
//    private String username;
//
//    @Value("${spring.security.user.password}")
//    private String password;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            if (username.equals(this.username)) {
//                return User.withUsername(username)
//                        .password(password)
//                        .roles("USER")
//                        .build();
//            } else {
//                throw new UsernameNotFoundException("User not found");
//            }
//        };
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService());
//        return builder.build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//               throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll())
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated());
//                .exceptionHandling(exHandling -> exHandling
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                .sessionManagement(sessMngr -> sessMngr
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .userDetailsService(userDetailsService());

//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}