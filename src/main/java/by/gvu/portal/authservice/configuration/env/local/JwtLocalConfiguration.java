package by.gvu.portal.authservice.configuration.env.local;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Profile("local")
public class JwtLocalConfiguration {
    @Bean
    public JWKSource<SecurityContext> localJwkSource(@Value("${jwt.keystore.type}") String keyStoreType,
                                                     @Value("${jwt.keystore.path}") String keyStorePath,
                                                     @Value("${jwt.keystore.password}") String keyStorePassword,
                                                     @Value("${jwt.keystore.alias}") String keyStoreAlias,
                                                     @Value("${jwt.keystore.id}") String keyStoreId) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        try (InputStream is = new ClassPathResource(keyStorePath).getInputStream()) {
            keyStore.load(is, keyStorePassword.toCharArray());
        }
        RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(keyStoreAlias, keyStorePassword.toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate(keyStoreAlias).getPublicKey();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(keyStoreId)
                .build();

        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }
}
