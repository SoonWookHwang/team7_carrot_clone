package com.innovation.team7_carrot_clone.security;

import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {
    }

    public UserDetails loadUserByUsername(String userPhoneNum) throws UsernameNotFoundException {
        User user = (User)this.userRepository.findByUserPhoneNum(userPhoneNum).orElseThrow(() -> {
            return new UsernameNotFoundException("Can't find " + userPhoneNum);
        });
        return new UserDetailsImpl(user);
    }
}
