package by.gvu.portal.authservice.configuration.env.prod;

import by.gvu.portal.authservice.utils.PemUtils;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Profile("prod")
public class JwtProdConfiguration {
    @Bean
    public JWKSource<SecurityContext> vaultJwkSource(@Value("${jwt.private-key}" ) String privateKeyPem,
                                                     @Value("${jwt.public-key}" ) String publicKeyPem) throws Exception {
        RSAPrivateKey privateKey = PemUtils.parseRSAPrivateKey(privateKeyPem);
        RSAPublicKey publicKey = PemUtils.parseRSAPublicKey(publicKeyPem);

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("jwt-vault-key")
                .build();

        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }
}
