package com.dyno.climb.user.domain.service;

import com.dyno.climb.user.application.port.out.UserPort;
import com.dyno.climb.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserPort userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByAccountId(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }
}
