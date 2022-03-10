package com.bootcamp.demo.Service;

import com.bootcamp.demo.Model.User;
import com.bootcamp.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository repo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = repo.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return new MemberUserDetails(user, repo);
  }

}
