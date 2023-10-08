package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import security.jwt.JwtAuthEntryPoint;

@Configuration
public class JwtConfig {

    @Bean
    public JwtAuthEntryPoint jwtAuthEntryPoint() {
        return new JwtAuthEntryPoint();
    }
}
