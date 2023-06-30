package com.jd.dao;

import com.jd.entity.RecycleShopping;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingMapper {
    /**
     * 根据用户id查询shopping中商品id，再联合goods表查询购物车所有商品信息【用户的购物车信息】
     *
     * @param user 用于存放用户id
     * @return
     */
    @Select("select s.user_id,s.goods_id,g.name goodsName,g.price goodsPrice,s.goods_count,s.create_time from goods g left join shopping s on g.id = s.goods_id where s.user_id = #{id}")
    List<ShoppingInfo> getShoppingInfoByUserId(User user);

    /**
     * 根据商品id和数量添加商品到购物车
     *
     * @param shoppingInfo
     */
    @Insert("insert into shopping(user_id, goods_id, goods_count, create_time) value (#{userId},#{goodsId},#{goodsCount},#{createTime})")
    void addShoppingInCarByGoodsId(ShoppingInfo shoppingInfo);

    /**
     * 修改购物车商品数量
     *
     * @param shoppingInfo
     */
    @Update("update shopping set goods_count = #{goodsCount},update_time = #{updateTime} where user_id = #{userId} and goods_id = #{goodsId}")
    void updateShoppingCount(ShoppingInfo shoppingInfo);

    /**
     * 删除购物车中的某些商品
     *
     * @param strIds 装商品id的字符串数组
     */
    void deleteShopping(String[] strIds, Integer userId);

    /**
     * 保存结算完的商品信息
     *
     * @param recycleShoppings 存放结算的商品信息列表
     */
    void buyShopping(List<RecycleShopping> recycleShoppings);

    /**
     * 删除购物车里面结算完的商品信息
     *
     * @param recycleShoppings 存放结算的商品信息列表
     */
    void deleteBuyShopping(List<RecycleShopping> recycleShoppings);

    /**
     * 获取结算的商品信息展示
     */
    @Select("select * from recycle where user_id = #{userId}")
    List<RecycleShopping> getBuyShopping(Integer userId);

    /**
     * 根据用户id和商品id来删除结算表中记录的商品
     *
     * @param userId  用户id
     * @param goodsId 商品id
     */
    @Delete("delete from recycle where user_id = #{userId} and goods_id = #{goodsId} and create_time = #{createTime}")
    void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId,String createTime);
}
