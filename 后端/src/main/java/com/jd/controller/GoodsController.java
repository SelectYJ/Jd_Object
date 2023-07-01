package com.jd.controller;

import com.jd.entity.*;
import com.jd.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("goods/")
public class GoodsController {

    @Autowired
    private GoodsService goodService;

    /**
     * 根据分页展示所有商品
     * @param page  第几页
     * @param count 每页多少条
     * @return
     * @throws InterruptedException
     */
    @GetMapping("show")
    public Result goodsShow(Integer page, Integer count) throws InterruptedException {
        log.info("商品展示页面：第{}页，每页{}条......", page, count);
        TimeUnit.MILLISECONDS.sleep(500);
        List<Goods> goodsInfo = goodService.getGoodsInfo(page, count);
        return Result.success(goodsInfo);
    }

    /**
     * 根据id展示一个商品
     * @param id 商品id
     * @return
     */
    @GetMapping("{id}")
    public Result goodShowById(@PathVariable Integer id) {
        log.info("展示id为{}的商品信息......", id);
        Goods goodInfo = goodService.getGoodsInfoById(id);
        return Result.success(goodInfo);
    }
}
