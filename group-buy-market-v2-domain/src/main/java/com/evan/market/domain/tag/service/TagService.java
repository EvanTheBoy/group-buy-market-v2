package com.evan.market.domain.tag.service;

import com.evan.market.domain.tag.adapter.repository.ITagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TagService {
    @Resource
    private ITagRepository repository;
}
