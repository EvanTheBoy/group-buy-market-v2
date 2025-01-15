package com.evan.market.domain.tag.adapter.repository;

import com.evan.market.domain.tag.model.entity.CrowdTagsJobEntity;

import java.util.List;

public interface ITagRepository {
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    public void addCrowdTagsUserId(String tagId, String userId);

    public void updateCrowdTagsStatistics(String tagId, List<String> userIdList);
}
