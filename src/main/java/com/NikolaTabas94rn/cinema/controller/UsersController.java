package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersController {
    @GetMapping
    @Operation(description = "Get all users", summary = "Get all users")
    List<UserDto> getUsers();

    @GetMapping("/{id}")
    @Operation(description = "Get one user", summary = "Get one user")
    UserDto getUser(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create user", summary = "Create user")
    UserDto saveUser(@RequestBody UserSaveDto user) throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update user", summary = "Update user")
    UserDto updateUser(@PathVariable int id, @RequestBody UserSaveDto updatedUser)throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @Operation(description = "Delete user", summary = "Delete user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeUser(@PathVariable int id)throws ResourceNotFoundException;
}
