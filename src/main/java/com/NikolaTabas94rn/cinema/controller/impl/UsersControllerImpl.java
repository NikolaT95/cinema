package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.UsersController;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSearchOption;
import com.NikolaTabas94rn.cinema.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Users")
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {
    private final UsersService usersService;

    @Override
    public Page<UserDto> getUsers(UserSearchOption userSearchOption) {
        return usersService.getAll(userSearchOption);
    }

    @Override
    public UserDto getUser(int id)throws ResourceNotFoundException {
        return usersService.getOne(id);
    }

    @Override
    public UserDto saveUser(UserSaveDto user) throws UniqueViolationException {
        return usersService.save(user);
    }

    @Override
    public UserDto updateUser(int id, UserSaveDto updatedUser)throws UniqueViolationException,ResourceNotFoundException {
        return usersService.update(id, updatedUser);
    }

    @Override
    public void removeUser(int id)throws ResourceNotFoundException {
        usersService.remove(id);
    }
}
