package com.evan.market.test.domain.tag;

import com.evan.market.domain.tag.service.ITagService;
import com.evan.market.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {
    @Resource
    private ITagService tagService;
    @Resource
    private IRedisService redisService;

    @Test
    public void testTagJob() {
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }

    @Test
    public void testGetTagBitmap() {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("Test result:{}", bitSet.set(redisService.getIndexFromUserId("Evan")));
        log.info("Test result:{}", bitSet.set(redisService.getIndexFromUserId("xiaofuge")));
    }
}
