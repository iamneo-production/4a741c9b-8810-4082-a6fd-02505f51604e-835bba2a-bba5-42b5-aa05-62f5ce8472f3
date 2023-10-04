package com.teamconnect.teamconnect.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.teamconnect.teamconnect.model.UserDetails;
import com.teamconnect.teamconnect.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public boolean createUser(UserDetails userDetails){
            if(userRepo.findByUserName(userDetails.getUserName()).isPresent()|| userRepo.findByEmail(userDetails.getEmail()).isPresent()){
                return false;
            }
            // userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            System.out.println(userDetails.getPassword());
            userRepo.save(userDetails);
            return true;
    }

    public UserDetails getDetails(UserDetails userDetails){
        // Optional<UserDetails> user = userRepo.findByUserNameAndPassword(userDetails.getUserName(), userDetails.getPassword());
        // UserDetails user = userRepo.findByUserNameAndPassword(userDetails.getUserName(), userDetails.getPassword());


        
        // if(user.isPresent())
        //     return user.get();

        //     return null;
        return userRepo.findByUserNameAndPassword(userDetails.getUserName(), userDetails.getPassword());
    }

    public List<UserDetails> getDemo(){
        return userRepo.findAll();
    }

    public boolean updateDetails(UserDetails userDetails,int id){
        Optional<UserDetails> user = userRepo.findById(id);

        if(user.isPresent()){
            UserDetails u = user.get();
            if(userDetails.getEmail() != null)
            u.setEmail(userDetails.getEmail());

            if(userDetails.getPassword() != null)
            u.setPassword(userDetails.getPassword());

            if(userDetails.getUserName() != null)
            u.setUserName(userDetails.getUserName());

            userRepo.save(u);
            return true;
        }

        return false;
    }

    public boolean deleteUser(int userId){
        userRepo.deleteById(userId);

        if(userRepo.findById(userId).isPresent())
            return false;
        else
            return true;
     }
}
