package com.jd.service.impl;

import com.jd.dao.ShoppingMapper;
import com.jd.entity.RecycleShopping;
import com.jd.entity.SearchShopping;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;
import com.jd.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public List<SearchShopping> searchFamily() {
        return shoppingMapper.searchFamily(null);
    }

    @Override
    public List<ShoppingInfo> searchShopping(Integer userId,String goodsName, Integer family, LocalDateTime startDateTimes,LocalDateTime endDateTimes) {
        return shoppingMapper.searchShopping(userId,goodsName,family,startDateTimes,endDateTimes);
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
        log.info("获取当前用户{}的购物车所有商品信息",user.getId());
        List<ShoppingInfo> oldShoppingInfos = this.searchShopping(user.getId(),null,null,null,null);
        for (int i = 0; i < oldShoppingInfos.size(); i++) {
            // 原来的购物车中用户id
            Integer oldShoppingUserId = oldShoppingInfos.get(i).getUserId();
            // 原来的购物车的商品id
            Integer oldShoppingGoodsId = oldShoppingInfos.get(i).getGoodsId();
            // 原来的购物车的商品数量
            Integer oldShoppingGoodsCount = oldShoppingInfos.get(i).getGoodsCount();
            if (oldShoppingUserId.equals(addShoppingUserId) && oldShoppingGoodsId.equals(addShoppingGoodsId)) {
                shoppingInfo.setGoodsCount(oldShoppingGoodsCount + addShoppingGoodsCount);
                log.info(">>>>>>>>>>>>>>已经存在{}，{}，直接更新......", oldShoppingUserId, oldShoppingGoodsId);
                this.updateShoppingCount(shoppingInfo);
                return;
            }
        }
        log.info(">>>>>>>>>>>>>>不存在【{}】，【{}】，直接添加一条......", addShoppingUserId, addShoppingGoodsId);
//        log.info(">>>>>>>>>>>>>>先根据商品类型的id为【{}】的商品来获取商品对应的商品类型名字......", shoppingInfo.getFamily());
//        List<SearchShopping> searchShoppingFamilies = shoppingMapper.searchFamily(shoppingInfo.getFamily());
//        shoppingInfo.setFamily(searchShoppingFamilies.get(0).getFamily());
//        log.info(">>>>>>>>>>>>>>然后添加商品类型为【{}】的商品......", searchShoppingFamilies.get(0).getFamily());
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
        return shoppingMapper.getBuyShopping(userId);
    }

    @Override
    public void deleteRecycleBuyShoppingByGoodsId(Integer userId, String goodsId,String createTime) {
        shoppingMapper.deleteRecycleBuyShoppingByGoodsId(userId,goodsId,createTime);
    }
}
