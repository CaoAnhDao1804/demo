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

    Role role = roleRepository.findById(traveler.getRoleId()).orElse(new Role());
    if (role.getId()!= null){
      if (role.getName().equals("admin")){
        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
      } else {
        List<ActionUser> listActionUser = permissService.getAllActionUser(traveler.getId());
        for (ActionUser actionUser: listActionUser){
          grantedAuthorities.add( new SimpleGrantedAuthority(actionUser.getName()));
        }
      }
    } else {
      grantedAuthorities.add(new SimpleGrantedAuthority("VIEW_PLACETYPE"));
      grantedAuthorities.add(new SimpleGrantedAuthority("VIEW_PLACECATEGORY"));
      grantedAuthorities.add(new SimpleGrantedAuthority("VIEW_LOCATION"));

    }

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
