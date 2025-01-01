package com.evan.market.test.infrastructure.dao;

import com.alibaba.fastjson.JSON;
import com.evan.market.infrastructure.dao.IGroupBuyActivityDao;
import com.evan.market.infrastructure.dao.po.GroupBuyActivity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupBuyActivityDaoTest {
    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Test
    public void testGroupBuyDiscountDao() {
        List<GroupBuyActivity> groupBuyActivities = groupBuyActivityDao.queryGroupBuyActivityList();
        log.info("Test result:{}", JSON.toJSONString(groupBuyActivities));
    }
}
