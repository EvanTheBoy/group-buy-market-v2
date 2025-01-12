package com.evan.market.domain.activity.service.discount.impl;

import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(GroupBuyActivityDiscountVO.GroupBuyDiscount discount) {
        return null;
    }
}
