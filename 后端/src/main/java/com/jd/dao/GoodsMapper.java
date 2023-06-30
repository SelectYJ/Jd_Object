package com.jd.dao;

import com.jd.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    /**
     * 获取所有商品信息,分页展示
     * @param page 页码
     * @param count 每一页的数量
     * @return
     */
    @Select("select * from goods limit #{page},#{count}")
    List<Goods> getGoodsInfo(Integer page,Integer count);

    /**
     * 获取单个商品信息
     * @param id  商品id
     * @return
     */
    @Select("select * from goods where id = #{id}")
    Goods getGoodInfoById(Integer id);
}
