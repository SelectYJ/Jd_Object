package com.jd.service;

import com.jd.entity.RecycleShopping;
import com.jd.entity.SearchShopping;
import com.jd.entity.ShoppingInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingService {

    /**
     * 查询所有的商品类型并返回
     * @return
     */
    List<SearchShopping> searchFamily();

    /**
     * 根据用户输入的条件查询购物车信息
     * @param goodsName 商品名字
     * @param familyId  商品类型
     * @param startDateTimes 开始时间
     * @param endDateTimes 结束时间
     * @return
     */
    List<ShoppingInfo> searchShopping(Integer userId,String goodsName, Integer familyId, LocalDateTime startDateTimes,LocalDateTime endDateTimes);

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
     * 根据用户id和商品id，以及创建结算商品的时间来删除结算表中记录的商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @param createTime 结算商品的时间
     */
    void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId,String createTime);
}
