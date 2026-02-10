package com.crudmvc.tareaRA3.configuracion;

import java.util.Set;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Seguridad {

    /** Acceso a AuthenticationManager */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /** Encriptar contraseñas */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // H2 console necesita iframes
        http.headers(h ->
                h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        http.authorizeHttpRequests(auth -> auth
                // estáticos
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                // público
                .requestMatchers("/login", "/").permitAll()

                // Usuarios normales
                .requestMatchers("/equipos", "/inicio").authenticated()
                
                .requestMatchers("/equipos/nuevo", "/equipos/*/editar")
                .hasAnyRole("MANAGER", "ADMIN")
                
                .requestMatchers("/usuarios/**")
                .hasAnyRole("MANAGER", "ADMIN")
                
                .requestMatchers("/equipos/*/eliminar")
                .hasRole("ADMIN")

                // el resto: autenticado
                .anyRequest().authenticated()
                
        );

        // login form
        http.formLogin(form -> form
        		.loginPage("/login")
                .defaultSuccessUrl("/inicio")
                .permitAll()
                
        );

        http.logout(logout -> logout.permitAll());

        return http.build();
    }
}
