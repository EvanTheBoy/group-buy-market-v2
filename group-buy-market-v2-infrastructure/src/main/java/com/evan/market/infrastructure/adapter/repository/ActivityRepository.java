package com.evan.market.infrastructure.adapter.repository;

import com.evan.market.domain.activity.adapter.repository.IActivityRepository;
import com.evan.market.domain.activity.model.valobj.DiscountTypeEnum;
import com.evan.market.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.evan.market.domain.activity.model.valobj.SCSkuActivityVO;
import com.evan.market.domain.activity.model.valobj.SkuVO;
import com.evan.market.infrastructure.dao.IGroupBuyActivityDao;
import com.evan.market.infrastructure.dao.IGroupBuyDiscountDao;
import com.evan.market.infrastructure.dao.ISCSkuActivityDao;
import com.evan.market.infrastructure.dao.ISkuDao;
import com.evan.market.infrastructure.dao.po.GroupBuyActivity;
import com.evan.market.infrastructure.dao.po.GroupBuyDiscount;
import com.evan.market.infrastructure.dao.po.SCSkuActivity;
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

    @Resource
    private ISCSkuActivityDao scSkuActivityDao;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);
        if (groupBuyActivityRes == null) {
            return null;
        }
        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        if (groupBuyDiscountRes == null) {
            return null;
        }
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.getByCode(groupBuyDiscountRes.getDiscountType()))
                .tagId(groupBuyDiscountRes.getTagId())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
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

    @Override
    public SCSkuActivityVO querySCSkuActivityVO(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityReq = new SCSkuActivity();
        scSkuActivityReq.setSource(source);
        scSkuActivityReq.setChannel(channel);
        scSkuActivityReq.setGoodsId(goodsId);
        SCSkuActivity scSkuActivityRes = scSkuActivityDao.querySCSkuActivityByGoodsId(scSkuActivityReq);
        if (scSkuActivityRes == null) {
            return null;
        }
        return SCSkuActivityVO.builder()
                .activityId(scSkuActivityRes.getActivityId())
                .channel(scSkuActivityRes.getChannel())
                .goodsId(scSkuActivityRes.getGoodsId())
                .source(scSkuActivityRes.getSource())
                .build();
    }
}
