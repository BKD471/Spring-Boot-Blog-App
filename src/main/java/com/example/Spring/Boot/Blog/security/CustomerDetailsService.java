package com.example.Spring.Boot.Blog.security;

import com.example.Spring.Boot.Blog.model.User;
import com.example.Spring.Boot.Blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //System.out.println("aya");
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id/username %s", usernameOrEmail)));

        //convert set of roles from user to set of grantedAuthority
        //spring security expects set of grantedAuthorities
        Set<GrantedAuthority> authorities = user.getRoles().stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());


        //conver user obj to spring security provided user obj
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
