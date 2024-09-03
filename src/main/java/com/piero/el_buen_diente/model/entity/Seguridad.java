package com.piero.el_buen_diente.model.entity;


import com.piero.el_buen_diente.Service.impl.UsuarioService;
import com.piero.el_buen_diente.model.dao.UsuarioDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class Seguridad  {

    Logger logger = LoggerFactory.getLogger(Seguridad.class);

    @Autowired
    UsuarioDao usuarioDao;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/resources/**", "/static/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/citas", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Desactivar CSRF si no es necesario

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioDao usuariodao, PasswordEncoder passwordEncoder) {

        return username -> {

            Usuario usuario = usuariodao.findByUsuario(username)
                    .orElseThrow(() -> {
                        logger.error("Usuario no encontrado: " + username);
                       return new UsernameNotFoundException("Usuario no encontrado");
                    });

            logger.info("Usuario encontrado: " + usuario.getUsuario());
            return new org.springframework.security.core.userdetails.User(
                    usuario.getUsuario(),
                    usuario.getContraseña(),
                    Collections.emptyList()
            );
        };
    }

}
