package com.jd.service.impl;

import com.jd.dao.UserMapper;
import com.jd.entity.User;
import com.jd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUserInfoByUsername(user);
    }

    @Override
    public void register(User user) {
        user.setName(user.getName() == null ? user.getEmailName().substring(0, user.getEmailName().indexOf("@")) : user.getName());
        user.setEmailName(user.getEmailName() == null ? null : user.getEmailName());
        user.setGender("男".equals(user.getGender()) ? "1" : "0");
        user.setUsername(user.getUsername() == null ? user.getEmailName() : user.getUsername());
        user.setPassword(user.getPassword() == null ? "123456" : user.getPassword());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertUser(user);
    }

    @Override
    public User getUserInfoByUsername(User user) {
        user.setUsername(user.getUsername() == null ? user.getEmailName() : user.getUsername());        User userInfo = userMapper.getUserInfoByUsername(user);
        if (userInfo == null) {
            return null;
        }
        userInfo.setGender("1".equals(userInfo.getGender()) ? "男" : "女");
        userInfo.setPassword("");
        return userInfo;
    }

    @Override
    public String getUserOldPasswordByUserId(Integer userId) {
        return userMapper.getOldPassword(userId);
    }

    @Override
    public void updateInfo(User user) {
        user.setGender("男".equals(user.getGender()) ? "1" : "0");
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateInfo(user);
    }

    @Transactional // 添加事务，要不全完成，要不全不完成
    @Override
    public void clearUser(Integer userId) {
        userMapper.clearUser(userId);
        userMapper.clearShoppingByUser(userId);
        userMapper.clearRecycleByUser(userId);
    }
}
