package com.jd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *  已经结算的购物车信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleShopping {
    private Integer id; // 当前数据id
    private Integer userId; // 用户id
    private Integer goodsId; // 商品id
    private String goodsName; // 商品名称
    private Double goodsPrice; // 商品价格
    private Integer goodsCount; // 商品数量
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
