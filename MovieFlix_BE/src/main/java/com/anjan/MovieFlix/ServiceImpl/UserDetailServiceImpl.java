package com.anjan.MovieFlix.ServiceImpl;

import com.anjan.MovieFlix.Model.User;
import com.anjan.MovieFlix.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userRepo.findByEmail(email);

        if(user==null)
        {
            System.out.println("User not found");
            throw  new UsernameNotFoundException("No user found with this username");
        }

        return user;
    }
}