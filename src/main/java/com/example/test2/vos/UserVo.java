package com.example.test2.vos;

import com.example.test2.entities.UserEntity;
import com.example.test2.enums.UserEnum;
import com.example.test2.interfaces.IResult;

public class UserVo extends UserEntity implements IResult<UserEnum> {
    private UserEnum result;
    @Override
    public UserEnum getResult() {
        return result;
    }

    @Override
    public void setResult(UserEnum result) {
        this.result = result;
    }
}
