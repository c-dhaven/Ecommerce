package com.project.Ecommerce.Config;

import com.project.Ecommerce.Entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Autowired
    JWTAuthenticationFilter jwtAuthFilter;
    @Autowired
    AuthenticationProvider authenticationProvider1;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/register", "/api/login").permitAll()
                        .requestMatchers("/admin/viewproduct", "/admin/viewcustomer", "/admin/editcustomerdetails", "/admin/editproductdetails")
                        .hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/product/viewproduct", "/product/addtocart", "/product/removecart/{id}", "/product/viewcart", "/product/viewbill")
                        .hasAuthority(Role.USER.name()).anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider1)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
