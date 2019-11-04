package com.example.test.serviceImpl;

import com.example.test.bean.UserBean;
import com.example.test.dto.LoginDto;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setName(name);
        loginDto.setPassword(password);
        return userMapper.getInfo(loginDto);
    }

    @Override
    public void  add(String name,String password){
        LoginDto loginDto = new LoginDto();
        loginDto.setName(name);
        loginDto.setPassword(password);
        userMapper.InsertInfo(loginDto);
    }
}
