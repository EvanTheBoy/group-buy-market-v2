package com.evan.market.domain.activity.service.discount;

import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        return null;
    }

    protected abstract BigDecimal doCalculate(GroupBuyActivityDiscountVO.GroupBuyDiscount discount);
}
