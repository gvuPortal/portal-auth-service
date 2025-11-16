package by.gvu.portal.authservice.configuration;

import by.gvu.portal.authservice.configuration.model.PortalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final PortalProperties portalProperties;

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(portalProperties.getSecurity().getEndpoints().getExclude().toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(portalProperties.getSecurity().getCsrf().getExclude().toArray(new String[0]))
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
