package com.jd.dao;

import com.jd.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper // 在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理
public interface UserMapper {
    /**
     * 根据用户名来查询用户信息
     *
     * @param user 用于获取用户的用户名
     * @return
     */
    @Select("select * from user where username = #{username} ")
    User getUserInfoByUsername(User user);

    /**
     * 注册用户，往用户数据表里面添加数据
     *
     * @param user 用户信息
     * @return
     */
    @Insert("insert into user(name, email_name, gender, username, password, create_time,update_time) value (#{name} ,#{emailName} , #{gender}, #{username}, #{password}, #{createTime}, #{updateTime})")
    boolean insertUser(User user);

    /**
     * 获取用户旧密码
     *
     * @param userId 用户id
     * @return 用户旧密码
     */
    @Select("select password from user where id = #{userId}")
    String getOldPassword(Integer userId);

    /**
     * 修改个人用户的信息
     *
     * @param user 存放用户id
     */
    @Update("update user set name=#{name},email_name = #{emailName},username=#{username},gender = #{gender},age=#{age},password=#{password},update_time=#{updateTime} where id = #{id}")
    void updateInfo(User user);

    /**
     * 注销用户
     *
     * @param userId 用户id
     */
    @Delete("delete from user where id = #{userId}")
    void clearUser(Integer userId);

    /**
     * 删除购物车中注销的用户的购物车
     *
     * @param userId 用户id
     */
    @Delete("delete from shopping where user_id = #{userId}")
    void clearShoppingByUser(Integer userId);

    /**
     * 删除结算表中注销的用户的结算商品
     *
     * @param userId 用户id
     */
    @Delete("delete from recycle where user_id = #{userId}")
    void clearRecycleByUser(Integer userId);

}
