package com.evan.market.infrastructure.dao;

import com.evan.market.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISCSkuActivityDao {
    SCSkuActivity querySCSkuActivityByGoodsId(SCSkuActivity scSkuActivity);
}
