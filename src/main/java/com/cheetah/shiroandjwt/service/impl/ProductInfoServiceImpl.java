package com.cheetah.shiroandjwt.service.impl;

import com.cheetah.shiroandjwt.entity.ProductInfo;
import com.cheetah.shiroandjwt.mapper.ProductInfoMapper;
import com.cheetah.shiroandjwt.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductInfoServiceImpl
 * @Description: TODO
 * @Date: 2020/9/27
 * @Author: cheetah
 * @Version: 1.0
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getProductInfoList() {
        return productInfoMapper.getProductInfoList();
    }
}
