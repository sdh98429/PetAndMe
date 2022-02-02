package com.sns.pet.service;

import com.sns.pet.dto.FeedDto;

import java.util.List;

public interface FeedService {
    public List<FeedDto> findMyFeedList(Long userNumber) throws Exception;
    public boolean addFeed(FeedDto feedDto) throws Exception;
    public FeedDto findFeed(Long feedNumber) throws Exception;
//    public boolean modifyFeed(FeedDto feedDto) throws Exception;
    public boolean removeFeed(Long feedNumber) throws Exception;

}
