package com.cheetah.shiroandjwt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: ProductInfo
 * @Description: 商品实体类
 * @Date: 2020/9/27
 * @Author: cheetah
 * @Version: 1.0
 */
@Data
@TableName(value="product_info")
public class ProductInfo {

    @TableId(value="id")
    private int id;
    private String name;
    private Double price;
    private Date createDate;
    private Date updateDate;
}
