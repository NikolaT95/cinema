package com.NikolaTabas94rn.cinema.model.mapper.util;

import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthoritiesMapper {
    @Named("mapAuthorities")
    public List<GrantedAuthority> mapAuthorities(boolean isAdmin) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(isAdmin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }
}
