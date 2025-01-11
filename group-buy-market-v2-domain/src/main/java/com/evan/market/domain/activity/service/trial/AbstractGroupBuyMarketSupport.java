package com.evan.market.domain.activity.service.trial;

import com.evan.market.domain.activity.adapter.repository.IActivityRepository;
import com.evan.market.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.evan.market.types.design.framework.tree.AbstractStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {

    protected long timeout = 500;

    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws InterruptedException, TimeoutException, ExecutionException {

    }
}
