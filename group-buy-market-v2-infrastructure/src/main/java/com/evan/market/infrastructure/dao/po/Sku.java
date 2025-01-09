package com.evan.market.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    private Long id;

    private String channel;

    private String source;

    private String goodsName;

    private String goodsId;

    private BigDecimal originalPrice;

    private Date createTime;

    private Date updateTime;
}
