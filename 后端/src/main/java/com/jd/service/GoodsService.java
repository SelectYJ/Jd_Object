package com.jd.service;

import com.jd.entity.Goods;
import com.jd.entity.ShoppingInfo;
import com.jd.entity.User;

import java.util.List;

public interface GoodsService {


    /**
     * 商品展示页面
     * @param page 页码
     * @param count 每一页的数量
     * @return
     */
    List<Goods> getGoodsInfo(Integer page,Integer count);

    /**
     * 根据商品id获取单个商品信息
     * @param id 商品id
     * @return
     */
    Goods getGoodsInfoById(Integer id);



}
