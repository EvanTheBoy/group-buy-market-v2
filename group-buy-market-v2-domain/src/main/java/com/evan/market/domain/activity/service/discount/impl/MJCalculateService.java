package com.evan.market.domain.activity.service.discount.impl;

import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.service.discount.AbstractDiscountCalculateService;
import com.evan.market.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        String marketExpr = groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x = new BigDecimal(split[0].trim());
        BigDecimal y = new BigDecimal(split[1].trim());
        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }
        BigDecimal deductedPrice = originalPrice.subtract(y);
        if (deductedPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        return deductedPrice;
    }
}
