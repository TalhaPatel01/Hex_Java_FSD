package com.springboot.myapp.config;

import com.springboot.myapp.model.Customer;
import com.springboot.myapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final UserService userService;

//    @Bean
//    public UserDetailsService users() {
//        UserDetails customer1 = User.builder()
//                .username("user1")
//                .password("{noop}user1@123")
//                .roles("CUSTOMER")
//                .build();
//        UserDetails customer2 = User.builder()
//                .username("user2")
//                .password("{noop}user2@123")
//                .roles("CUSTOMER")
//                .build();
//        UserDetails executive = User.builder()
//                .username("executive")
//                .password("{noop}executive@123")
//                .roles("EXECUTIVE")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin@123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(customer1,customer2,executive,admin);
//    }

    @Bean
    public SecurityFilterChain TRSFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.OPTIONS,"/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/ticket/add/{customerId}")
                        .hasAnyRole("CUSTOMER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/customer/sign-up")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/get-all")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/get/{id}")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/customer/{customerId}/v1")
                        .hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/ticket/assign-executive/{ticketId}/{executiveId}")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }
}
