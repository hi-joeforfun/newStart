package com.example.test.mapper;

import com.example.test.bean.UserBean;
import com.example.test.dto.LoginDto;

public interface UserMapper {

    UserBean getInfo(LoginDto loginDto);


    void  InsertInfo(LoginDto loginDto);
}
