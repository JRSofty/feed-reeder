package com.jrsofty.web.feeder.security;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.jrsofty.web.feeder.commons.logging.LogUtil;

@Configuration
@PropertySource("file:${feeder.home}/conf/feeder.properties")
@EnableWebSecurity
public class SecurityConfig {

    private static Logger LOG = LogUtil.getLogger(SecurityConfig.class);

    @Value("${feeder.home}")
    private String feederHome;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests(requests -> requests.requestMatchers(new
        // RequestMatcher).permitAll().anyRequest.authenticated()).formLogin(form
        // -> form.loginPage("/login").permitAll())
        // .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new HibernateUserDetailsService();
    }

}
