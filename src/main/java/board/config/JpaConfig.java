package board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    //    @Bean
//    AuditorAware<String> auditorAware() {
//        return () -> Optional.of("admin");
//    }
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(
                        SecurityContextHolder.getContext().getAuthentication()
                )
                .filter(auth -> auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
                .map(auth -> auth.getName()); // 보통 uid 또는 username
    }

}
