package com.cobranet.secrethitler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class MyUserCtrl {

    @Autowired
    MyUserRepo myUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public MyUser signup(@RequestBody MyUser user) {
        MyUser myuser = myUserRepo.findByEmail(user.getEmail());
        if( myuser != null) {
            throw new RuntimeException("Email " +  user.getEmail() + " is already in use");
        }
        user.setImage("noimage");
        user.setRole("user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        myUserRepo.save(user);
        user.setPassword("");
        return user;
    }
    @PostMapping("/updateme")
    public MyUser update(@AuthenticationPrincipal  MyUser user,@RequestBody MyUser newuser){
        MyUser old = myUserRepo.findByEmail(newuser.getEmail());
        if( old == null) {
            throw new RuntimeException("There is no user with email " +  user.getEmail());
        }
        old.setEmail(newuser.getEmail());
        old.setImage(newuser.getImage());
        old.setRole("user");
        old.setPassword(bCryptPasswordEncoder.encode(newuser.getPassword()));
        myUserRepo.save(old);
        old.setPassword("");
        return old;
    }

}
