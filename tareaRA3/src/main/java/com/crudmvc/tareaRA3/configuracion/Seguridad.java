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
    public UserDetailsService users(PasswordEncoder passwordEncoder) {

        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();

        UserDetails admin1 = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("admin1"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin1);
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
                .requestMatchers("/", "/login").permitAll()

                // Usuarios normales
                .requestMatchers("/equipos").authenticated()
                
                // H2 solo ADMIN
                .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
                .requestMatchers("/h2-console/**").hasRole("ADMIN")

                // el resto: autenticado
                .anyRequest().authenticated()
                
        );

        // CSRF: desactivar solo para H2
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
                .ignoringRequestMatchers("/h2-console/**", "/h2/**")
        );

        // login form
        http.formLogin(form -> form
        		.loginPage("/login")
                .defaultSuccessUrl("/equipos")
                .permitAll()
                
        );

        http.logout(logout -> logout.permitAll());

        return http.build();
    }
}
