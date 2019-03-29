package com.cobranet.secrethitler.service;

import com.cobranet.secrethitler.user.MyUser;
import com.cobranet.secrethitler.user.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    @Autowired
    MyUserRepo userRepo;
    public MyUser getUser(Principal p){
        MyUser user = userRepo.findByEmail(p.getName());
        return user;
    }
}
