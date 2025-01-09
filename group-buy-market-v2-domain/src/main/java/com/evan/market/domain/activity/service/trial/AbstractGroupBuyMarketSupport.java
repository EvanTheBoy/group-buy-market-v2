package com.evan.market.domain.activity.service.trial;

import com.evan.market.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.evan.market.types.design.framework.tree.AbstractStrategyRouter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {
    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws InterruptedException, TimeoutException, ExecutionException {

    }
}
