package com.example.test.service;

import com.example.test.bean.UserBean;

public interface UserService {

    UserBean loginIn(String name,String password);

    void  add(String name,String password);
}
