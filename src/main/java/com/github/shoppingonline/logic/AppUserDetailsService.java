package com.github.shoppingonline.logic;

import com.github.shoppingonline.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserService userService;

    public AppUserDetailsService(UserService service) {
        this.userService = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole().name()));

        return buildUserForAuthentication(user, authority);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authority) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                true, true, true, true, authority);
    }
}
