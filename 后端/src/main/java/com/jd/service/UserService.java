package com.jd.service;

import com.jd.entity.User;

public interface UserService {
    /**
     * 判断登录
     * @param user 用来获取用户名
     * @return
     */
    User login(User user);

    /**
     * 判断注册
     * @param user
     */
    void register(User user);

    /**
     * 获取用户信息
     * @param user 用来获取用户名
     * @return
     */
    User getUserInfoByUsername(User user);

    /**
     * 用于获取用户旧密码
     * @param userId 用户id
     * @return
     */
    String getUserOldPasswordByUserId(Integer userId);
    /**
     * 修改个人信息
     * @param user
     */
    void updateInfo(User user);

    /**
     * 注销账号
     * @param id 用户id
     */
    void clearUser(Integer id);
}
