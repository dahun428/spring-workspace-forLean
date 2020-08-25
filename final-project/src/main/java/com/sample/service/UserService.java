package com.sample.service;

import java.util.List;

import com.sample.dto.UserInfoDto;
import com.sample.web.view.Coupon;
import com.sample.web.view.User;
import com.sample.web.view.UserPoint;

public interface UserService {
    void addUser(User user);
    User loginUser(String id, String password);
    void updateUser(User user);
    User getUserDetail(String id);
    List<Coupon> getCouponByUserId(String userId);
    
    User getUserByEmail(String email);
    User getPasswordByEmail(String email);
    List<UserInfoDto> getAllUsers();
    
    UserInfoDto getUserInfoDetail(String id);
    
    void addUserPointHistory(UserPoint userPoint);
    
}