package com.example.carshopwithspringboot.service;

import com.example.carshopwithspringboot.dto.UserDto;
import com.example.carshopwithspringboot.model.User;
import com.example.carshopwithspringboot.repository.AuthRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(AuthRepository authRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = authRepository.findUserByUserName(username);
        UserDetails authUser = null;
        if (dbUser != null) {
            authUser = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return List.of(new SimpleGrantedAuthority("ROLE_" + dbUser.getRole()));
                }

                @Override
                public String getPassword() {
                    return dbUser.getPassword();
                }

                @Override
                public String getUsername() {
                    return dbUser.getUserName();
                }
            };
        }
        return authUser;
    }

    public User save(UserDto userDto) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(userDto);
        User user = objectMapper.readValue(s, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = authRepository.save(user);
        return save;
    }
}
