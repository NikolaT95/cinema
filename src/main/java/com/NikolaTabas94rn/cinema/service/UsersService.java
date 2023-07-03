package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import com.NikolaTabas94rn.cinema.model.mapper.UserMapper;
import com.NikolaTabas94rn.cinema.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll(){
        return usersRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getOne(int id){
        UserEntity userEntity=usersRepository.findById(id).orElse(null);

        return userMapper.toDto(userEntity);

    }

    public UserDto save(UserSaveDto user){
        if(usersRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException();
        }

        UserEntity userEntity=userMapper.toEntity(user);
        usersRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    public UserDto update(int id, UserSaveDto updatedUser){
        UserEntity user=usersRepository.findById(id).orElse(null);

        if(!user.getEmail().equals(updatedUser.getEmail())
        && usersRepository.findByEmail(updatedUser.getEmail()).isPresent()){
            throw new RuntimeException();
        }

        UserEntity updatedUserEntity=userMapper.toEntity(updatedUser);
        updatedUserEntity.setId(id);

        updatedUserEntity.setReservations(user.getReservations());

        usersRepository.save(updatedUserEntity);

        return userMapper.toDto(updatedUserEntity);
    }
    public void remove(int id){
        if(!usersRepository.existsById(id)){
            throw new RuntimeException();
        }

        usersRepository.deleteById(id);
    }
}
