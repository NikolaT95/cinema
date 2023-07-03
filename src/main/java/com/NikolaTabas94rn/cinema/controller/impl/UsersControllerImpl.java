package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.UsersController;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {
    private final UsersService usersService;

    @Override
    public List<UserDto> getUsers() {
        return usersService.getAll();
    }

    @Override
    public UserDto getUser(int id) {
        return usersService.getOne(id);
    }

    @Override
    public UserDto saveUser(UserSaveDto user) {
        return usersService.save(user);
    }

    @Override
    public UserDto updateUser(int id, UserSaveDto updatedUser) {
        return usersService.update(id, updatedUser);
    }

    @Override
    public void removeUser(int id) {
        usersService.remove(id);
    }
}
