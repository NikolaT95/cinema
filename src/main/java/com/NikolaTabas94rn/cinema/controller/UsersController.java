package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSearchOption;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//cisto
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersController {
    @GetMapping
    @Operation(description = "Get all users", summary = "Get all users")
    Page<UserDto> getUsers(@ParameterObject UserSearchOption userSearchOption);

    @GetMapping("/{id}")
    @Operation(description = "Get one user", summary = "Get one user")
    UserDto getUser(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create user", summary = "Create user")
    UserDto saveUser(@Valid @RequestBody UserSaveDto user) throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update user", summary = "Update user")
    UserDto updateUser(@PathVariable int id, @Valid @RequestBody UserSaveDto updatedUser)throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @Operation(description = "Delete user", summary = "Delete user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeUser(@PathVariable int id)throws ResourceNotFoundException;
}
