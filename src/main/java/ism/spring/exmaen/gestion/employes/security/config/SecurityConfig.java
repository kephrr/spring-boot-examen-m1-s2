package ism.spring.exmaen.gestion.employes.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private  final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return  daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf(AbstractHttpConfigurer::disable)//csrf->csrf.disable()
               .formLogin(form->form
                       .loginPage("/login")
                       .permitAll()
               ).authorizeHttpRequests(auth->auth
                       .requestMatchers("/admin/**").hasAnyAuthority("Admin")
                       .requestMatchers("/employes/create").hasAnyAuthority("Admin")
                       .requestMatchers("/departements/create").hasAnyAuthority("Admin")
                       .requestMatchers("/**").permitAll()
                       .anyRequest().authenticated())
               .build();
    }





}
