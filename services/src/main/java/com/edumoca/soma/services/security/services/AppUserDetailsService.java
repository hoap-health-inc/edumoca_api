package com.edumoca.soma.services.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.edumoca.soma.entities.AppUser> user = userRepository.findByLoginId(username);
        if (user.isPresent()) {
            com.edumoca.soma.entities.AppUser user1 = user.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            user1.getRoles().stream().forEach(r->authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
            return new User(user1.getLoginId(), user1.getPassword(),authorities);
        } else {
            throw  new DataNotFoundException();
        }
    }

}
