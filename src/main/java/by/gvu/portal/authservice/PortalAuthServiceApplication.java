package by.gvu.portal.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PortalAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalAuthServiceApplication.class, args);
    }

}