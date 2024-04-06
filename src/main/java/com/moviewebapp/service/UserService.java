package com.moviewebapp.service;


import com.moviewebapp.model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);


}
