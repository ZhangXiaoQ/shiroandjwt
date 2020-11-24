package com.cheetah.shiroandjwt.service;

import com.cheetah.shiroandjwt.entity.ProductInfo;
import java.util.List;

/**
 * @ClassName: ProductInfoService
 * @Description: 商品service
 * @Date: 2020/9/27
 * @Author: cheetah
 * @Version: 1.0
 */
public interface ProductInfoService {

    List<ProductInfo> getProductInfoList();
}
