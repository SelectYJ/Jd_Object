package com.jd.service.impl;

import com.jd.dao.GoodsMapper;
import com.jd.entity.Goods;
import com.jd.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoodsInfo(Integer page, Integer count) {
        Integer nowSum = (page - 1) * 5;
        return goodsMapper.getGoodsInfo(nowSum, count);
    }

    @Override
    public Goods getGoodsInfoById(Integer id) {
        return goodsMapper.getGoodInfoById(id);
    }
}
