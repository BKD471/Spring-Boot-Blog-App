package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.LoginDto;
import com.example.Spring.Boot.Blog.dto.SignUpDto;
import com.example.Spring.Boot.Blog.exception.BlogApiException;
import com.example.Spring.Boot.Blog.model.Role;
import com.example.Spring.Boot.Blog.model.User;
import com.example.Spring.Boot.Blog.repository.RolesRepository;
import com.example.Spring.Boot.Blog.repository.UserRepository;
import com.example.Spring.Boot.Blog.security.JwtTokenProvider;
import com.example.Spring.Boot.Blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private  final RolesRepository rolesRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,RolesRepository rolesRepository ,PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider){
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.rolesRepository=rolesRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
    }
    @Override
    public String login(LoginDto loginDto) {

        //Lets authenticate using authmanager
       Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));

       //once we get auth obj store it in spring security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String SignUp(SignUpDto signUpDto) {
        //check whther user exists in datbase
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,String.format("User with userName %s already exists in database",signUpDto.getUsername()));
        }

        //check whether email existsInDatabase
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,String.format("User with email %s already exists in database",signUpDto.getEmail()));
        }

        //Create User obj with detials from incoing signUpDto obj
        User userCreated=setUser(signUpDto);
        return "User Registered Successfully";
    }

    private User setUser(SignUpDto signUpDto){
        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Set<Role> roles=new HashSet<>();
        Role userRole=rolesRepository.findByName("ROLE_USER").get();
        //Optional.get() can be used instead of thowing OrElseThroe  returns NoSuchElementFound if nothing found

        roles.add(userRole);
        user.setRoles(roles);

        User savedUser=userRepository.save(user);
        return savedUser;
    }
}
