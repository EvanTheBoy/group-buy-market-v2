package com.evan.market.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketProductEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 来源
     */
    private String source;
    /**
     * 渠道
     */
    private String channel;
}
