package com.example.moviewebapp.service;


import com.example.moviewebapp.model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);


}
