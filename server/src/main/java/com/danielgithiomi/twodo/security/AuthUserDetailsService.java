package com.danielgithiomi.twodo.security;

import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String upperCaseUsername = username.toUpperCase();

        User dbUser = this.userRepository.findUserByUsername(upperCaseUsername)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username " + username + " was found in the database."));

        return new AuthUser(dbUser);
    }
}
