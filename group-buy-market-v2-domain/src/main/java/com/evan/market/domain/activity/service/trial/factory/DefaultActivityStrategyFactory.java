package com.evan.market.domain.activity.service.trial.factory;

import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;
import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.model.valobj.SkuVO;
import com.evan.market.domain.activity.service.trial.node.RootNode;
import com.evan.market.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> getStrategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        private SkuVO skuVO;

        private BigDecimal deductedPrice;
    }
}
