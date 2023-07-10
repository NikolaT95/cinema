package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSearchOption;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import com.NikolaTabas94rn.cinema.model.mapper.UserMapper;
import com.NikolaTabas94rn.cinema.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public Page<UserDto> getAll(UserSearchOption userSearchOption){
        int page = 0;
        if(userSearchOption.getPage() != null && userSearchOption.getPage() > 0) {
            page = userSearchOption.getPage() - 1;
        }

        int pageSize = 10;
        if(userSearchOption.getPageSize() != null && userSearchOption.getPageSize() > 0) {
            pageSize = userSearchOption.getPageSize();
        }
        return usersRepository.findAll(PageRequest.of(page, pageSize))
                .map(userMapper::toDto);
    }

    public UserDto getOne(int id)throws ResourceNotFoundException {
        UserEntity userEntity=usersRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        return userMapper.toDto(userEntity);

    }

    public UserDto save(UserSaveDto user) throws UniqueViolationException {
        if(usersRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        UserEntity userEntity=userMapper.toEntity(user);
        usersRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    public UserDto update(int id, UserSaveDto updatedUser)throws UniqueViolationException,ResourceNotFoundException{
        UserEntity user=usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        if(!user.getEmail().equals(updatedUser.getEmail())
        && usersRepository.findByEmail(updatedUser.getEmail()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        UserEntity updatedUserEntity=userMapper.toEntity(updatedUser);
        updatedUserEntity.setId(id);

        updatedUserEntity.setReservations(user.getReservations());

        usersRepository.save(updatedUserEntity);

        return userMapper.toDto(updatedUserEntity);
    }
    public void remove(int id) throws ResourceNotFoundException{
        if(!usersRepository.existsById(id)){
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.USER);
        }

        usersRepository.deleteById(id);
    }
}
