package com.jd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 展示用户购物车的信息，联合两张表的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingInfo {
    private Integer id; // 当前数据id
    private Integer userId; // 用户id
    private Integer goodsId; // 商品id
    private String goodsName; // 商品名称
    private Double goodsPrice; // 商品价格
    private Integer goodsCount; // 商品数量
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
}
