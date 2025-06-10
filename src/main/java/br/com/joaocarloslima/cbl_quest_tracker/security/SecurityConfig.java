package br.com.joaocarloslima.cbl_quest_tracker.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        //.requestMatchers(HttpMethod.PATCH,"/task/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login") 
                        .defaultSuccessUrl("/post-login", true)
                        .permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var users = List.of(
                User.withUsername("team01").password("{noop}").roles("USER").build(),
                User.withUsername("team02").password("{noop}").roles("USER").build(),
                User.withUsername("team03").password("{noop}").roles("USER").build(),
                User.withUsername("team04").password("{noop}").roles("USER").build(),
                User.withUsername("team05").password("{noop}").roles("USER").build(),
                User.withUsername("team06").password("{noop}").roles("USER").build(),
                User.withUsername("team07").password("{noop}").roles("USER").build()
        );
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    CorsConfigurationSource corsConfig(){
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }    

}
