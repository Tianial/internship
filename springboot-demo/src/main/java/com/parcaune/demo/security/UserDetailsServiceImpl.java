package com.parcaune.demo.security;

import com.parcaune.demo.user.Role;
import com.parcaune.demo.user.User;
import com.parcaune.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service

public class UserDetailsServiceImpl implements UserDetailsService  //

{

    @Autowired
    private UserRepository userRepository; //for researches in DB

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username); // goes in Db and finds  if the username enter by login is present

        if (user == null) throw new UsernameNotFoundException(username);// If username not found in Db this line is executed if not goes back to linee 35 of AuthController

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
