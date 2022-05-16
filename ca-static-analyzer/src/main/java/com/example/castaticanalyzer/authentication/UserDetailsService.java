package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.authentication.user.UserDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @UseCase */

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDataGateway gateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return gateway.findByUsername(username);
    }
}
