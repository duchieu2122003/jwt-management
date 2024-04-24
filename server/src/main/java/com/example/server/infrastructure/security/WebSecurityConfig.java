package com.example.server.infrastructure.security;

import com.example.server.infrastructure.constant.ActorConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author duchieu212
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/security/**").permitAll()
                                .requestMatchers("/api/admin/**").hasAuthority(ActorConstant.ACTOR_ADMIN)
                                .requestMatchers("/api/manager/**").hasAuthority(ActorConstant.ACTOR_MANAGER)
                                .requestMatchers("/api/staff/**").hasAuthority(ActorConstant.ACTOR_STAFF)
                                .requestMatchers("/api/common/**").hasAnyAuthority(ActorConstant.ACTOR_ADMIN,
                                ActorConstant.ACTOR_MANAGER, ActorConstant.ACTOR_STAFF)
                                .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
