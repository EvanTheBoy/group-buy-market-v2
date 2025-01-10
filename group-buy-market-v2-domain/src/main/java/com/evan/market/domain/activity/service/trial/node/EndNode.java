package com.evan.market.domain.activity.service.trial.node;

import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;
import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.model.valobj.SkuVO;
import com.evan.market.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.evan.market.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.evan.market.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class EndNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        SkuVO skuVO = dynamicContext.getSkuVO();
        return TrialBalanceEntity.builder()
                .endTime(groupBuyActivityDiscountVO.getEndTime())
                .startTime(groupBuyActivityDiscountVO.getStartTime())
                .targetCount(groupBuyActivityDiscountVO.getTarget())
                .goodsId(skuVO.getGoodsId())
                .goodsName(skuVO.getGoodsName())
                .originalPrice(skuVO.getOriginalPrice())
                .deductionPrice(new BigDecimal("0.00"))
                .isEnable(false)
                .isVisible(false)
                .build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultHandler;
    }
}
