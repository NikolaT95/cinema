package com.NikolaTabas94rn.cinema.service;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSearchOption;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import com.NikolaTabas94rn.cinema.model.mapper.UserMapper;
import com.NikolaTabas94rn.cinema.repository.UsersRepository;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UsersService usersService;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        public void shouldGetAllUsers( ){
            final UserSearchOption searchOptions = mock(UserSearchOption.class);
            final Page<UserEntity> expectedUsers = mock(Page.class);
            when(usersRepository.findAll(any(Pageable.class))).thenReturn(expectedUsers);
            final Page<UserDto> actualUsers = usersService.getAll(searchOptions);
    }

    @Test
    public void testGetOneValidUser() throws ResourceNotFoundException {

        int userId = 1;

        UserEntity userEntity = UserEntity.builder()
                .id(userId)
                .email("test@example.com")
                .build();

        UserDto expectedUserDto = UserDto.builder()
                .id(userId)
                .email("test@example.com")
                .build();

        when(usersRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(expectedUserDto);


        UserDto actualUserDto = usersService.getOne(userId);


        assertEquals(expectedUserDto, actualUserDto);
        verify(usersRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).toDto(userEntity);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetOneNonExistentUser() throws ResourceNotFoundException {

        int userId = 1;

        when(usersRepository.findById(userId)).thenReturn(Optional.empty());


        usersService.getOne(userId);


    }

    @Test
    public void testSaveNewUser() throws UniqueViolationException {

        UserSaveDto userSaveDto = UserSaveDto.builder()
                .email("test@example.com")
                .password("password123")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .email("test@example.com")
                .password("password123")
                .build();

        UserDto expectedUserDto = UserDto.builder()
                .id(1)
                .email("test@example.com")
                .build();

        when(usersRepository.findByEmail(userSaveDto.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toEntity(userSaveDto)).thenReturn(userEntity);
        when(usersRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toDto(userEntity)).thenReturn(expectedUserDto);


        UserDto actualUserDto = usersService.save(userSaveDto);


        assertEquals(expectedUserDto, actualUserDto);
        verify(usersRepository, times(1)).findByEmail(userSaveDto.getEmail());
        verify(userMapper, times(1)).toEntity(userSaveDto);
        verify(usersRepository, times(1)).save(userEntity);
        verify(userMapper, times(1)).toDto(userEntity);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test(expected = UniqueViolationException.class)
    public void testSaveUserWithDuplicateEmail() throws UniqueViolationException {

        UserSaveDto userSaveDto = UserSaveDto.builder()
                .email("test1@example.com")
                .password("password123")
                .build();

        when(usersRepository.findByEmail(userSaveDto.getEmail())).thenReturn(Optional.of(mock(UserEntity.class)));

        thrown.expect(UniqueViolationException.class);
        thrown.expectMessage("'email' already exists");


        usersService.save(userSaveDto);


    }

    @Test
    public void testUpdateUser() throws UniqueViolationException, ResourceNotFoundException {

        int userId = 1;
        UserSaveDto updatedUserDto = UserSaveDto.builder()
                .email("newemail@example.com")
                .password("newpassword123")
                .build();

        UserEntity existingUserEntity = UserEntity.builder()
                .id(userId)
                .email("oldemail@example.com")
                .password("oldpassword123")
                .build();

        when(usersRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));
        when(usersRepository.findByEmail(updatedUserDto.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toEntity(updatedUserDto)).thenReturn(existingUserEntity);


        UserDto result = usersService.update(userId, updatedUserDto);


        verify(usersRepository, times(1)).findById(userId);
        verify(usersRepository, times(1)).findByEmail(updatedUserDto.getEmail());
        verify(userMapper, times(1)).toEntity(updatedUserDto);
        verify(usersRepository, times(1)).save(existingUserEntity);
        verify(userMapper, times(1)).toDto(existingUserEntity);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateUserResourceNotFound() throws UniqueViolationException, ResourceNotFoundException {

        int userId = 1;
        UserSaveDto updatedUserDto = UserSaveDto.builder()
                .email("newemail@example.com")
                .password("newpassword123")
                .build();

        when(usersRepository.findById(userId)).thenReturn(Optional.empty());


        usersService.update(userId, updatedUserDto);


    }

    @Test(expected = UniqueViolationException.class)
    public void testUpdateUserDuplicateEmail() throws UniqueViolationException, ResourceNotFoundException {

        int userId = 1;
        UserSaveDto updatedUserDto = UserSaveDto.builder()
                .email("newemail@example.com")
                .password("newpassword123")
                .build();

        UserEntity existingUserEntity = UserEntity.builder()
                .id(userId)
                .email("oldemail@example.com")
                .password("oldpassword123")
                .build();

        when(usersRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));
        when(usersRepository.findByEmail(updatedUserDto.getEmail())).thenReturn(Optional.of(mock(UserEntity.class)));


        usersService.update(userId, updatedUserDto);


    }
    @Test
    public void testRemoveUser() throws ResourceNotFoundException {

        int userId = 1;

        when(usersRepository.existsById(userId)).thenReturn(true);


        usersService.remove(userId);


        verify(usersRepository, times(1)).existsById(userId);
        verify(usersRepository, times(1)).deleteById(userId);
    }
    @Test(expected = ResourceNotFoundException.class)
    public void testRemoveUserNotFound() throws ResourceNotFoundException {

        int userId = 1;

        when(usersRepository.existsById(userId)).thenReturn(false);


        usersService.remove(userId);


    }
}
