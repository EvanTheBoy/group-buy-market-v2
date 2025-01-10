package com.evan.market.infrastructure.adapter.repository;

import com.evan.market.domain.activity.adapter.repository.IActivityRepository;
import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.model.valobj.SkuVO;
import com.evan.market.infrastructure.dao.IGroupBuyActivityDao;
import com.evan.market.infrastructure.dao.IGroupBuyDiscountDao;
import com.evan.market.infrastructure.dao.ISkuDao;
import com.evan.market.infrastructure.dao.po.GroupBuyActivity;
import com.evan.market.infrastructure.dao.po.GroupBuyDiscount;
import com.evan.market.infrastructure.dao.po.Sku;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Resource
    private ISkuDao skuDao;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel) {
        GroupBuyActivity groupBuyActivityReq = new GroupBuyActivity();
        groupBuyActivityReq.setSource(source);
        groupBuyActivityReq.setChannel(channel);
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivity(groupBuyActivityReq);

        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(groupBuyDiscountRes.getDiscountType())
                .tagId(groupBuyDiscountRes.getTagId())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .channel(groupBuyActivityRes.getChannel())
                .source(groupBuyActivityRes.getSource())
                .groupBuyDiscount(groupBuyDiscount)
                .tagId(groupBuyActivityRes.getTagId())
                .groupType(groupBuyActivityRes.getGroupType())
                .tagScope(groupBuyActivityRes.getTagScope())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .validTime(groupBuyActivityRes.getValidTime())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .goodsId(groupBuyActivityRes.getGoodsId())
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuByGoodsId(goodsId);

        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }
}
