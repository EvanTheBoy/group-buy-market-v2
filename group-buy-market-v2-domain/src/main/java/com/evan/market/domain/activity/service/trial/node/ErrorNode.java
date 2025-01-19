package com.evan.market.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;
import com.evan.market.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.evan.market.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.evan.market.types.design.framework.tree.StrategyHandler;
import com.evan.market.types.enums.ResponseCode;
import com.evan.market.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-NoMarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));
        if (dynamicContext.getGroupBuyActivityDiscountVO() == null || dynamicContext.getSkuVO() == null) {
            log.info("商品无拼团营销配置 {}", requestParameter.getGoodsId());
            throw new AppException(ResponseCode.E0002.getCode(), ResponseCode.E0002.getInfo());
        }
        return TrialBalanceEntity.builder().build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultHandler;
    }
}
