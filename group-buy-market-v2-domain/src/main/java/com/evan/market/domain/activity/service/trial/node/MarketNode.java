package com.evan.market.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.evan.market.domain.activity.model.entity.MarketProductEntity;
import com.evan.market.domain.activity.model.entity.TrialBalanceEntity;
import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.model.valobj.SkuVO;
import com.evan.market.domain.activity.service.discount.IDiscountCalculateService;
import com.evan.market.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.evan.market.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.evan.market.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.evan.market.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import com.evan.market.types.design.framework.tree.StrategyHandler;
import com.evan.market.types.enums.ResponseCode;
import com.evan.market.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private ErrorNode errorNode;

    @Resource
    private TagNode tagNode;

    @Resource
    private Map<String, IDiscountCalculateService> discountCalculateServiceMap;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws InterruptedException, TimeoutException, ExecutionException {
        // First thread, 异步查询活动配置
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new QueryGroupBuyActivityDiscountVOThreadTask(requestParameter.getSource(), requestParameter.getChannel(), requestParameter.getGoodsId(), repository);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);
        // Second thread, 异步查询商品信息 - 在实际生产中，商品有同步库或者调用接口查询。这里暂时使用DB方式查询。
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);

        dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES));
        dynamicContext.setSkuVO(skuVOFutureTask.get(timeout, TimeUnit.MINUTES));

        log.info("拼团商品查询试算服务-MarketNode userId:{} 异步线程加载数据「GroupBuyActivityDiscountVO、SkuVO」完成", requestParameter.getUserId());
    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        if (groupBuyActivityDiscountVO == null) {
            return router(requestParameter, dynamicContext);
        }
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = groupBuyActivityDiscountVO.getGroupBuyDiscount();
        SkuVO skuVO = dynamicContext.getSkuVO();
        if (groupBuyDiscount == null || skuVO == null) {
            return router(requestParameter, dynamicContext);
        }
        IDiscountCalculateService discountCalculateService = discountCalculateServiceMap.get(groupBuyDiscount.getMarketPlan());
        if (discountCalculateService == null) {
            log.info("不存在{}类型的折扣计算服务，支持类型为:{}", groupBuyDiscount.getMarketPlan(), JSON.toJSONString(discountCalculateServiceMap.keySet()));
            throw new AppException(ResponseCode.E0001.getCode(), ResponseCode.E0001.getInfo());
        }
        BigDecimal deductedPrice = discountCalculateService.calculate(requestParameter.getUserId(), skuVO.getOriginalPrice(), groupBuyDiscount);
        dynamicContext.setDeductedPrice(deductedPrice);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        if (dynamicContext.getGroupBuyActivityDiscountVO() == null
                || dynamicContext.getSkuVO() == null
                || dynamicContext.getDeductedPrice() == null) {
            return errorNode;
        }
        return tagNode;
    }
}
