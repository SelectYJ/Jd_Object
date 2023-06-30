package com.jd.controller;

import com.jd.entity.Result;
import com.jd.entity.User;
import com.jd.service.UserService;
import com.jd.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login") // 用户登录
    public Result userLogin(@RequestBody User user, HttpServletResponse response) {
        log.info("用户登录：{}", user);
        User user1 = userService.login(user);
        // 如果密码为空或者为null
        if (user1 == null) {
            return Result.error("用户名不存在！");
        }
        if (!(user1.getPassword()).equals(user.getPassword())) {
            return Result.error("密码错误！");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user1.getUsername());
        claims.put("userId", user1.getId());
        String jwt = JwtUtil.getJWT(claims);
        response.addHeader("Access-Control-Expose-Headers", "token");
        response.addHeader("token", jwt);
        return Result.success();
    }

    @PostMapping("/register") // 用户注册
    public Result userRegister(@RequestBody User user) {
        log.info("用户注册：{}", user);
        User user1 = userService.getUserInfoByUsername(user);
        if (user1 == null) {
            userService.register(user);
            return Result.success();
        }
        return Result.error("账号名已存在！");
    }

    @PostMapping("/userInfo") // 获取用户信息
    public Result userInfo(@RequestBody User user) {
        log.info("访问了用户个人信息：{}", user);
        User userInfo = userService.getUserInfoByUsername(user);
        return Result.success(userInfo);
    }

    @PostMapping("/getUserOldPasswordByUserId") // 获取用户旧密码
    public Result getOldPassword(@RequestBody User user) {
        String oldPassword = userService.getUserOldPasswordByUserId(user.getId());
        log.info("用户传来的用户id：{}，用户旧密码：{}，查到的旧密码：{}", user.getId(), user.getPassword(), oldPassword);
        if (oldPassword.equals(user.getPassword())) {
            return Result.success();
        }
        return Result.error("旧密码错误");
    }

    @PostMapping("/updateInfo") // 修改用户信息
    public Result updateUserInfo(@RequestBody User user) {
        log.info("修改个人信息为：{}", user);
        userService.updateInfo(user);
        return Result.success();
    }

    @PostMapping("/clearUser") // 注销用户
    public Result clearUser(@RequestBody User user) {
        log.info("注销用户id为{}的用户，并清空其购物车......", user.getId());
        userService.clearUser(user.getId());
        return Result.success();
    }
}
