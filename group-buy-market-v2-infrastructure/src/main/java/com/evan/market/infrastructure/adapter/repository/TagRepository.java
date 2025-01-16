package com.evan.market.infrastructure.adapter.repository;

import com.evan.market.domain.tag.adapter.repository.ITagRepository;
import com.evan.market.domain.tag.model.entity.CrowdTagsJobEntity;
import com.evan.market.infrastructure.dao.ICrowdTagsDao;
import com.evan.market.infrastructure.dao.ICrowdTagsDetailDao;
import com.evan.market.infrastructure.dao.ICrowdTagsJobDao;
import com.evan.market.infrastructure.dao.po.CrowdTags;
import com.evan.market.infrastructure.dao.po.CrowdTagsDetail;
import com.evan.market.infrastructure.dao.po.CrowdTagsJob;
import com.evan.market.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
        CrowdTagsJob crowdTagsJobReq = new CrowdTagsJob();
        crowdTagsJobReq.setTagId(tagId);
        crowdTagsJobReq.setBatchId(batchId);
        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        if (crowdTagsJobRes == null) {
            return null;
        }
        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetailReq = new CrowdTagsDetail();
        crowdTagsDetailReq.setTagId(tagId);
        crowdTagsDetailReq.setUserId(userId);
        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId));
        } catch (DuplicateKeyException ignored) {

        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int size) {
        CrowdTags crowdTagsReq = new CrowdTags();
        crowdTagsReq.setTagId(tagId);
        crowdTagsReq.setStatistics(size);
        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }
}
