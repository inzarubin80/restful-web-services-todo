package com.zarubin.rest.webservices.restfulwebservices.jwt;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  //static List<com.zarubin.rest.webservices.restfulwebservices.jwt.JwtUserDetails> inMemoryUserList = new ArrayList<>();

  @Autowired
  UserJpaRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = repository.findByUsername(username);
    JwtUserDetails jwtUserDetails = null;
    if (user != null) {
      jwtUserDetails = new JwtUserDetails(user.getId(), user.getUsername(),
              user.getPassword(), user.getRole());
    } else {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }
    return jwtUserDetails;
  }
}


