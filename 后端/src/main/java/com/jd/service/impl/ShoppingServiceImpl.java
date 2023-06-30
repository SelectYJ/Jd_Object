package com.jd.service.impl;

import com.jd.dao.ShoppingMapper;
import com.jd.entity.RecycleShopping;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;
import com.jd.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public List<ShoppingInfo> getShoppingInfoByUserId(User user) {
        List<ShoppingInfo> shoppingInfo = shoppingMapper.getShoppingInfoByUserId(user);
        return shoppingInfo;
    }

    @Override
    public void addShoppingInCarByGoodsId(ShoppingInfo shoppingInfo) {
        // 要添加的商品的用户的id
        Integer addShoppingUserId = shoppingInfo.getUserId();
        // 要添加的商品的id
        Integer addShoppingGoodsId = shoppingInfo.getGoodsId();
        // 要添加的商品的数量
        Integer addShoppingGoodsCount = shoppingInfo.getGoodsCount();
        User user = new User();
        user.setId(addShoppingUserId);
        // 获取当前用户的购物车所有商品信息
        List<ShoppingInfo> oldShoppingInfos = this.getShoppingInfoByUserId(user);
        for (int i = 0; i < oldShoppingInfos.size(); i++) {
            // 原来的购物车中用户id
            Integer oldShoppingUserId = oldShoppingInfos.get(i).getUserId();
            // 原来的购物车的商品id
            Integer oldShoppingGoodsId = oldShoppingInfos.get(i).getGoodsId();
            // 原来的购物车的商品数量
            Integer oldShoppingGoodsCount = oldShoppingInfos.get(i).getGoodsCount();
            if (oldShoppingUserId.equals(addShoppingUserId) && oldShoppingGoodsId.equals(addShoppingGoodsId)) {
                shoppingInfo.setGoodsCount(oldShoppingGoodsCount + addShoppingGoodsCount);
                this.updateShoppingCount(shoppingInfo);
                log.info(">>>>>>>>>>>>>>已经存在{}，{}", oldShoppingUserId, oldShoppingGoodsId);
                return;
            }
        }
        log.info(">>>>>>>>>>>>>>不存在{}，{}", addShoppingUserId, addShoppingGoodsId);
        shoppingMapper.addShoppingInCarByGoodsId(shoppingInfo);
    }

    @Override
    public void updateShoppingCount(ShoppingInfo shoppingInfo) {
        shoppingMapper.updateShoppingCount(shoppingInfo);
    }

    @Override
    public void deleteShopping(String[] strIds,Integer userId) {
        shoppingMapper.deleteShopping(strIds,userId);
    }

    @Override
    public void buyShopping(List<RecycleShopping> recycleShoppings) {
        shoppingMapper.buyShopping(recycleShoppings);
    }

    @Override
    public void deleteBuyShopping(List<RecycleShopping> recycleShoppings) {
        shoppingMapper.deleteBuyShopping(recycleShoppings);
    }

    @Override
    public List<RecycleShopping> getBuyShopping(Integer userId) {
        List<RecycleShopping> getBuyShopping = shoppingMapper.getBuyShopping(userId);
        return getBuyShopping;
    }

    @Override
    public void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId,String createTime) {
        shoppingMapper.deleteRecycleBuyShoppingByGoodsId(userId,goodsId,createTime);
    }

}
