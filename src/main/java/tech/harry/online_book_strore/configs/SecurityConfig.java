package tech.harry.online_book_strore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()

                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/rest/v1/auth/**","/rest/v1/bookCategory/**","/rest/v1/books/**","/rest/v1/userLogin/**","/rest/api/cart/**","/rest/api/cart/**","/rest/api/orders/**").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic();

        return httpSecurity.build();
    }
}

