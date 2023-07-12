package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.CinemaUserDetails;
import com.NikolaTabas94rn.cinema.model.api.user.UserDto;
import com.NikolaTabas94rn.cinema.model.api.user.UserSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import com.NikolaTabas94rn.cinema.model.mapper.util.AuthoritiesMapper;
import com.NikolaTabas94rn.cinema.model.mapper.util.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses ={ AuthoritiesMapper.class, PasswordEncoderMapper.class})
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "password", qualifiedByName = "encodePassword")
    UserEntity toEntity(UserSaveDto userSaveDto);

    @Mappings({
            @Mapping(source = "email", target = "username"),
            @Mapping(source = "admin", target = "authorities", qualifiedByName = "mapAuthorities")
    })
    CinemaUserDetails toUserDetails(UserEntity userEntity);
}
