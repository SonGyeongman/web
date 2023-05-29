package com.example.test2.entities;

public class UserEntity {
    private int index;
    private String name;
    private String email;
    private String password;
    private String phone;


    public UserEntity() {
    }

    public UserEntity(int index, String name, String email, String password, String phone) {
        this.index = index;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
