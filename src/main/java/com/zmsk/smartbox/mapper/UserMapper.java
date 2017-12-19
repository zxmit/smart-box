package com.zmsk.smartbox.mapper;

import com.zmsk.smartbox.pojo.User;

import java.util.Map;

public interface UserMapper {

    public User selectByWxId(String wxId);

    public User selectByPhoneNumber(String phoneNumber);

    public int addUserWithPhoneNumber(User user);

}
