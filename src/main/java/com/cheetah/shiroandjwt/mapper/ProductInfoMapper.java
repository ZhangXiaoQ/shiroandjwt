package com.cheetah.shiroandjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheetah.shiroandjwt.entity.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

    List<ProductInfo> getProductInfoList();
}
