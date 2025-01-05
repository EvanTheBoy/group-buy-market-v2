package com.evan.market.domain.activity.service.trial.node;

import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;
import com.evan.market.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.evan.market.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.evan.market.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EndNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
