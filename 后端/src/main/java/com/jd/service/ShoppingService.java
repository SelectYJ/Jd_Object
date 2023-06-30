package com.jd.service;

import com.jd.entity.RecycleShopping;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;

import java.util.List;

public interface ShoppingService {

    /**
     * 获取用户购物车商品信息
     *
     * @param user 用于存放用户id
     * @return
     */
    List<ShoppingInfo> getShoppingInfoByUserId(User user);

    /**
     * 修改购物车商品数量
     *
     * @param shoppingInfo 存放用户id，商品id,商品名字，商品数量
     */
    void updateShoppingCount(ShoppingInfo shoppingInfo);

    /**
     * 根据商品id和数量添加商品到购物车
     *
     * @param shoppingInfo 存放用户id，商品id,商品名字，商品数量
     */
    void addShoppingInCarByGoodsId(ShoppingInfo shoppingInfo);

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
     * 删除购物车里面结算完的商品信息 存放结算的商品信息列表
     * @param recycleShoppings
     */
    void deleteBuyShopping(List<RecycleShopping> recycleShoppings);

    /**
     * 获取结算的商品信息展示
     *
     * @return
     */
    List<RecycleShopping> getBuyShopping(Integer username);

    /**
     * 根据用户id和商品id来删除结算表中记录的商品
     * @param userId 用户id
     * @param goodsId 商品id
     */
    void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId,String createTime);
}
