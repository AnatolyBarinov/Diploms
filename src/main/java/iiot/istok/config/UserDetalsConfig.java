package iiot.istok.config;

import iiot.istok.repository.UserRepository;
import iiot.istok.service.impl.JpaUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class UserDetalsConfig {

    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService jpaUserDetailsService(UserRepository userRepository) {
        return new JpaUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}