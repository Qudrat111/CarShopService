package com.example.carshopwithspringboot.controller;

import com.example.carshopwithspringboot.config.security.jwt.JwtProvider;
import com.example.carshopwithspringboot.dto.UserDto;
import com.example.carshopwithspringboot.model.User;
import com.example.carshopwithspringboot.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "AuthController", description = "This is for authentication for user")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtProvider jwtProvider;

    public AuthController(UserDetailsServiceImpl userDetailsService, JwtProvider jwtProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @Operation(description = "This will do register the user", responses = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "This returns User in json "
            ))
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(
            @Valid
            @RequestBody UserDto userDto,
            BindingResult bindingResult
    ) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User save = userDetailsService.save(userDto);
        return ResponseEntity.ok(save);
    }

    @Operation(description = "This will do login the user", responses = {
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
    },
            parameters = {
                    @Parameter(
                            required = true, name = "username", description = "It is username of User"
                    ), @Parameter(
                    required = true, name = "password", description = "It is password of User"
            )
            })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null && userDetails.getPassword().equals(password)) {
            String token = jwtProvider.generateToken(username);
            return new ResponseEntity<>(Map.of("token", token), HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(description = "It is logout method")
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> logout() {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
