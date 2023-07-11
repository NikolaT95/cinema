package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.model.mapper.UserMapper;
import com.NikolaTabas94rn.cinema.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    private final UserMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .map(usersMapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));
    }
}
