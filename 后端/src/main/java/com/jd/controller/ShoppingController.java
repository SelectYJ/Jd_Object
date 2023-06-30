package com.jd.controller;

import com.jd.entity.RecycleShopping;
import com.jd.entity.Result;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;
import com.jd.service.ShoppingService;
import com.jd.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("shopping/")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("increaseGoods") // 添加商品
    public Result addGood(ShoppingInfo shoppingInfo, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        // 解析jwt获得用户名
        Claims claims = JwtUtil.parseJWT(jwt);
        shoppingInfo.setUserId((Integer) claims.get("userId"));
        shoppingInfo.setCreateTime(LocalDateTime.now());
        log.info("用户【{}】添加id为【{}】的商品【{}】条", shoppingInfo.getUserId(), shoppingInfo.getGoodsId(), shoppingInfo.getGoodsCount());
        shoppingService.addShoppingInCarByGoodsId(shoppingInfo);
        return Result.success();
    }

    @PostMapping("show") // 根据用户id展示购物车信息
    public Result shoppingShow(@RequestBody User user, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        user.setId((Integer) claims.get("userId"));
        log.info("正在获取用户【{}】的购物车：{}", user.getUsername(), user.getId());
        List<ShoppingInfo> ShoppingInfo = shoppingService.getShoppingInfoByUserId(user);
        return Result.success(ShoppingInfo);
    }

    @GetMapping("updateCount/{goodsId},{count}") // 根据用户id修改购物车的数量
    public Result updateShoppingCount(@PathVariable Integer goodsId, @PathVariable Integer count, HttpServletRequest request) {
        ShoppingInfo shoppingInfo = new ShoppingInfo();
        // 获取用户传的token头部
        String jwt = request.getHeader("token");
        // 解析token
        Claims claims = JwtUtil.parseJWT(jwt);
        shoppingInfo.setUserId((Integer) claims.get("userId"));
        shoppingInfo.setGoodsId(goodsId);
        shoppingInfo.setGoodsCount(count);
        log.info("根据user_id去【更新】shopping表中商品的信息......", shoppingInfo.getUserId());
        shoppingService.updateShoppingCount(shoppingInfo);
        return Result.success();
    }

    @GetMapping("deleteShopping") // 删除购物车商品根据商品id和用户id
    public Result deleteShoppingByGoodsId(String ids, HttpServletRequest request) {
        String[] strIds = ids.split(",");
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("删除购物车中用户id为{}的商品id为{}的商品", claims.get("userId"), ids);
        shoppingService.deleteShopping(strIds, (Integer) claims.get("userId"));
        return Result.success(strIds);
    }

    @Transactional
    @PostMapping("buyShopping")
    public Result buyGoods(@RequestBody Map<Object, List<RecycleShopping>> buyShopping) {
        log.info("结算的所有商品信息：{}", buyShopping);
        // 得到Map集合的key
        Object mapKey = buyShopping.keySet().iterator().next();
        List<RecycleShopping> recycleShoppings = buyShopping.get(mapKey);
        shoppingService.buyShopping(recycleShoppings);
        shoppingService.deleteBuyShopping(recycleShoppings);
        return Result.success();
    }

    @GetMapping("getBuyShopping")
    public Result getBuyShopping(HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("获取用户id为{}的结算的商品信息展示", claims.get("userId"));
        List<RecycleShopping> recycleShopping = shoppingService.getBuyShopping((Integer) claims.get("userId"));
        return Result.success(recycleShopping);
    }

    @GetMapping("/deleteRecycleShoppingByGoodsId")
    public Result deleteRecycleShoppingByGoodsId(String goodsId,String createTime , HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("删除结算表中用户id为{}的商品id为{}，创建时间为{}的商品", claims.get("userId"), goodsId,createTime);
        shoppingService.deleteRecycleBuyShoppingByGoodsId((Integer) claims.get("userId"), goodsId,createTime);
        return Result.success();
    }

}
