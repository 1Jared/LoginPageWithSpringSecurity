package com.example.employ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<User> user=userRepository.findByUserName(userName);

       System.out.println(user.toString());

       user.orElseThrow( ()-> new UsernameNotFoundException("Not Found" + userName));

       return  user.map(MyUserDetails::new).get();

    }
}
