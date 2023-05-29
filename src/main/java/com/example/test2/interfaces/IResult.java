package com.example.test2.interfaces;

public interface IResult <T extends Enum<?>>{
    T getResult();

    void setResult(T t);
}
