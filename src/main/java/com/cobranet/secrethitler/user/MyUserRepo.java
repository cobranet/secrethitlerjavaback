/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobranet.secrethitler.user;

import com.cobranet.secrethitler.user.MyUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Cobranet
 */
public interface MyUserRepo extends PagingAndSortingRepository <MyUser,Long>   {

  public MyUser findByEmail(String Email);

}
