package com.jd.dao;

import com.jd.entity.RecycleShopping;
import com.jd.entity.SearchShopping;
import com.jd.entity.ShoppingInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ShoppingMapper {

    /**
     * 查询所有的商品分类
     * 不传参数则查询所有分类
     * 当传入参数时根据分类id查询的商品分类名字
     *
     * @return
     */
    List<SearchShopping> searchFamily(String familyId);

    /**
     * 根据用户输入的条件查询购物车信息
     * 当没有输入条件的时候查询该用户下的所有购物车信息
     * 根据用户id查询shopping中商品id，再联合goods表查询购物车所有商品信息【用户的购物车信息】
     *
     * @param userId   用户id
     * @param goodsName   商品名称
     * @param family    商品类型
     * @param startDateTimes 开始时间
     * @param endDateTimes 结束时间
     * @return
     */
    List<ShoppingInfo> searchShopping(Integer userId, String goodsName, Integer family,LocalDateTime startDateTimes,LocalDateTime endDateTimes);

    /**
     * 根据商品id和数量添加商品到购物车
     *
     * @param shoppingInfo
     */
    @Insert("insert into shopping(user_id, goods_id , family , goods_count, create_time) " +
            "value (#{userId},#{goodsId},#{family},#{goodsCount},#{createTime})")
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
    void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId, String createTime);
}
