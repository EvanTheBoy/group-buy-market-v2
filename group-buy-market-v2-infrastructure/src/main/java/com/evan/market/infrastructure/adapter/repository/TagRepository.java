package com.evan.market.infrastructure.adapter.repository;

import com.evan.market.domain.tag.adapter.repository.ITagRepository;
import com.evan.market.domain.tag.model.entity.CrowdTagsJobEntity;
import com.evan.market.infrastructure.dao.ICrowdTagsDao;
import com.evan.market.infrastructure.dao.ICrowdTagsDetailDao;
import com.evan.market.infrastructure.dao.ICrowdTagsJobDao;
import com.evan.market.infrastructure.dao.po.CrowdTagsJob;
import com.evan.market.infrastructure.redis.IRedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;
    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private IRedisService redisService;

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {

        return null;
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {

    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, List<String> userIdList) {

    }
}
