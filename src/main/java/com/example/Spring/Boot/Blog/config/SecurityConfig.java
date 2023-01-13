package com.example.Spring.Boot.Blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity  // adding security to method level
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    public SecurityConfig(UserDetailsService userDetailsService){
        System.out.println("efgwrg");
        this.userDetailsService=userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        System.out.println("wrgrg");
        return new BCryptPasswordEncoder();
    }


    //After 5.2 spring security automatically provides UserDetailsService and passwordEncode to AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Only the get requests will be accesible to any admin/users
    //otherwise to create,update delette , you need to be admin
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests((auth)->
                auth.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                        .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
//In memory authentication
//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails bhaskar= User.builder().username("Bhaskar").password(passwordEncoder().encode("Bhaskar")).roles("USER").build();
//        UserDetails admin= User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(bhaskar,admin);
//    }
  }

