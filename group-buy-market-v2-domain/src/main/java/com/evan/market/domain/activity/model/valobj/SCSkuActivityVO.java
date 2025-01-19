package com.evan.market.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {
    /*
    来源
     */
    private String source;
    /*
    渠道
     */
    private String channel;
    /*
    商品ID
     */
    private String goodsId;

    /*
    活动ID
     */
    private Long activityId;
}
