package by.gvu.portal.authservice.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class DemoUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // demo user: alice / password
        if ("alice".equals(username) || "user".equals(username)) {
            return User.withUsername(username)
                    .password(encoder.encode("password"))
                    .authorities(List.of())
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
