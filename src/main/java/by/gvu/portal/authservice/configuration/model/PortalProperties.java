package by.gvu.portal.authservice.configuration.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "portal")
@Data
public class PortalProperties {

    private Security security = new Security();

    @Data
    public static class Security {

        private Csrf csrf = new Csrf();
        private Endpoints endpoints = new Endpoints();

        @Data
        public static class Csrf {
            private List<String> exclude;
        }

        @Data
        public static class Endpoints {
            private List<String> exclude;
        }
    }
}
