package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersController {
    @GetMapping
    List<UserDto> getUsers();

    @GetMapping("/{id}")
    UserDto getUser(@PathVariable int id);

    @PostMapping()
    UserDto saveUser(@RequestBody UserSaveDto user);

    @PutMapping("/{id}")
    UserDto updateUser(@PathVariable int id, @RequestBody UserSaveDto updatedUser);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeUser(@PathVariable int id);
}
