package com.example.carshopwithspringboot.controller;

import com.example.carshopwithspringboot.dto.UserDto;
import com.example.carshopwithspringboot.model.User;
import com.example.carshopwithspringboot.service.impl.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            description = "This will edit exist user",
            responses = {
                    @ApiResponse(description = "User updated successfully", responseCode = "202"),
                    @ApiResponse(description = "Bad request something was wrong", responseCode = "400"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "You should give me user in request body and I will return it in json"
            ),
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of user which you want update"
                    )
            }
    )
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PostMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(
            @Valid
            @PathVariable(name = "id") Integer id,
            @RequestBody UserDto userDto,
            BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userDto.setId(id);
        User update = userService.update(userDto);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @Operation(description = "This is for deleting user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
            },
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of user which you want delete"
                    )
            })
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteUser(
            @PathVariable(name = "id") Integer id) {
        Integer delete = userService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @Operation(description = "This is for getting all user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Access denied"),
            }
    )
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
