package com.evan.market.domain.activity.service;

import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;

public interface IIndexGroupBuyMarketService {
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProduct) throws Exception;
}
