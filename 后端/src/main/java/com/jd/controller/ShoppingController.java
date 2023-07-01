package com.jd.controller;

import com.jd.entity.*;
import com.jd.service.ShoppingService;
import com.jd.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("shopping/")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    /**
     * 查询所有的商品分类
     *
     * @return
     */
    @GetMapping("searchFamily")
    public Result searchFamily() {
        log.info("查询所有的商品分类......");
        return Result.success(shoppingService.searchFamily());
    }

    /**
     * 根据用户输入的条件查询购物车信息
     * 当没有输入条件的时候查询该用户下的所有购物车信息
     * 根据用户id查询shopping中商品id，再联合goods表查询购物车所有商品信息【用户的购物车信息】
     * @param goodsName 商品名字
     * @param family    商品类型
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    @GetMapping("searchShopping")
    public Result searchShopping(String goodsName, Integer family, String startDate,String endDate, HttpServletRequest request) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTimes = null;
        LocalDateTime endDateTimes = null;
        if(StringUtils.hasLength(startDate)&&!"null".equals(startDate)){
            startDateTimes = LocalDateTime.parse(startDate, fmt);
        }
        if(StringUtils.hasLength(endDate)&&!"null".equals(endDate)){
            endDateTimes = LocalDateTime.parse(endDate, fmt);
        }
        String jwt = request.getHeader("token");
        Integer userId = (Integer) JwtUtil.parseJWT(jwt).get("userId");
        log.info("根据用户输入的条件商品名称：{}，商品类型：{}，时间范围：{}-{}来查询购物车信息......", goodsName, family, startDateTimes, endDateTimes);
        List<ShoppingInfo> searchShoppingInfos = shoppingService.searchShopping(userId, goodsName, family, startDateTimes, endDateTimes);
        return Result.success(searchShoppingInfos);
    }

    /**
     * 添加商品到购物车
     *
     * @param shoppingInfo 商品信息
     * @param request
     * @return
     */
    @GetMapping("increaseGoods")
    public Result addGood(ShoppingInfo shoppingInfo, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        // 解析jwt获得用户名
        Claims claims = JwtUtil.parseJWT(jwt);
        shoppingInfo.setUserId((Integer) claims.get("userId"));
        shoppingInfo.setCreateTime(LocalDateTime.now());
        log.info("用户【{}】添加id为【{}】，类型为【{}】的商品【{}】条", shoppingInfo.getUserId(), shoppingInfo.getGoodsId(), shoppingInfo.getFamily(), shoppingInfo.getGoodsCount());
        shoppingService.addShoppingInCarByGoodsId(shoppingInfo);
        return Result.success();
    }

    /**
     * 根据用户id修改购物车的数量
     *
     * @param goodsId 要更新的商品id
     * @param count   更新的商品数量
     * @param request
     * @return
     */
    @GetMapping("updateCount/{goodsId},{count}")
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

    /**
     * 删除购物车商品根据商品id和用户id
     *
     * @param ids     删除的商品id
     * @param request
     * @return
     */
    @GetMapping("deleteShopping")
    public Result deleteShoppingByGoodsId(String ids, HttpServletRequest request) {
        String[] strIds = ids.split(",");
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("删除购物车中用户id为{}的商品id为{}的商品", claims.get("userId"), ids);
        shoppingService.deleteShopping(strIds, (Integer) claims.get("userId"));
        return Result.success(strIds);
    }

    /**
     * 结算的商品，结算后并删除购物车中商品
     *
     * @param buyShopping
     * @return
     */
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

    /**
     * 获取所有结算的商品信息
     *
     * @param request
     * @return
     */
    @GetMapping("getBuyShopping")
    public Result getBuyShopping(HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("获取用户id为{}的结算的商品信息展示", claims.get("userId"));
        List<RecycleShopping> recycleShopping = shoppingService.getBuyShopping((Integer) claims.get("userId"));
        return Result.success(recycleShopping);
    }

    /**
     * 根据用户id和商品id，以及创建结算商品的时间来删除结算表中记录的商品
     *
     * @param goodsId    商品id
     * @param createTime 商品结算的时间
     * @param request
     * @return
     */
    @GetMapping("/deleteRecycleShoppingByGoodsId")
    public Result deleteRecycleShoppingByGoodsId(String goodsId, String createTime, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        log.info("删除结算表中用户id为{}的商品id为{}，创建时间为{}的商品", claims.get("userId"), goodsId, createTime);
        shoppingService.deleteRecycleBuyShoppingByGoodsId((Integer) claims.get("userId"), goodsId, createTime);
        return Result.success();
    }

}
