package com.evan.market.domain.activity.service.discount;

import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public interface IDiscountCalculateService {
    BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
}
