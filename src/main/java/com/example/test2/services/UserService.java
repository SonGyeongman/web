package com.example.test2.services;

import com.example.test2.entities.UserEntity;
import com.example.test2.enums.UserEnum;
import com.example.test2.mappers.IUserMapper;
import com.example.test2.vos.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void login(UserVo userVo){
        UserEntity userEntity = this.userMapper.selectUser(userVo.getEmail(), userVo.getPassword());
        if(userEntity == null){
            userVo.setResult(UserEnum.FAILURE);
            return;
        }
        userVo.setEmail(userEntity.getEmail());
        userVo.setPassword(userEntity.getPassword());
        userVo.setIndex(userEntity.getIndex());
        userVo.setName(userEntity.getName());
        userVo.setPhone(userEntity.getPhone());
        userVo.setResult(UserEnum.SUCCESS);
    }

    public void register(UserVo userVo){
        if(this.userMapper.insertUser(userVo) > 0){
            userVo.setResult(UserEnum.SUCCESS);
            return;
        }
        userVo.setResult(UserEnum.FAILURE);
    }

    public String emailCheck(String email){
        if(this.userMapper.selectEmailCheck(email) > 0){
            return "FAILURE";
        }
        return "SUCCESS";
    }

    public String phoneCheck(String phone){
        if(this.userMapper.selectPhoneCheck(phone) > 0){
            return "FAILURE";
        }
        return "SUCCESS";
    }

    public UserEntity sessionUser(int index){
        return this.userMapper.selectUserSession(index);
    }
}
