package com.evan.market.infrastructure.dao;

import com.evan.market.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISkuDao {
    Sku querySkuByGoodsId(String goodsId);
}
