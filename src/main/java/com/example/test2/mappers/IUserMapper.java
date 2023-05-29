package com.example.test2.mappers;

import com.example.test2.entities.UserEntity;
import com.example.test2.vos.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Mapper
public interface IUserMapper {
    UserEntity selectUser(@Param(value = "email") String email,
                      @Param(value = "password") String password);

    int insertUser(UserEntity userEntity);

    int selectEmailCheck(@Param(value = "email") String email);

    int selectPhoneCheck(@Param(value = "phone") String phone);

    UserEntity selectUserSession(@Param(value = "index") int index);
}

