package com.config;


import com.entity.Role;
import com.entity.Traveler;
import com.model.ActionUser;
import com.repository.RoleRespository;
import com.repository.TravelerResponsitory;
import com.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private TravelerResponsitory travelerResponsitory;

  @Autowired
  private RoleRespository roleRepository;

  @Autowired
  PermissService permissService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Traveler traveler = travelerResponsitory.findByUsername(username);

    if (traveler == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }


    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    List<ActionUser> listActionUser = permissService.getAllActionUser(traveler.getId());

    for (ActionUser actionUser: listActionUser){
      grantedAuthorities.add( new SimpleGrantedAuthority(actionUser.getName()));
    }
//    Role role = roleRepository.findById(traveler.getRoleId()).orElse(new Role());
////
//    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));




    return org.springframework.security.core.userdetails.User//
            .withUsername(username)//
            .password(traveler.getPassword())//
            .authorities(grantedAuthorities)//
            .accountExpired(false)//
            .accountLocked(false)//
            .credentialsExpired(false)//
            .disabled(false)//
            .build();
  }

}
