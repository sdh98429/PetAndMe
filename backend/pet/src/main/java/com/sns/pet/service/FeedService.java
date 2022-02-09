package com.sns.pet.service;

import com.sns.pet.dto.CommentDto;
import com.sns.pet.dto.FeedDto;

import java.util.List;

public interface FeedService {
    List<FeedDto> findNewsFeedList(Long userNumber, String cursor) throws Exception;
    List<FeedDto> findFavFeedList(Long userNumber, String cursor) throws Exception;
    List<FeedDto> findFollowFeedList(Long userNumber, String cursor) throws Exception;
    List<FeedDto> findMyFeedList(Long userNumber) throws Exception;
    boolean addFeed(FeedDto feedDto) throws Exception;
    FeedDto findFeed(Long userNumber, Long feedNumber) throws Exception;
//    boolean modifyFeed(FeedDto feedDto) throws Exception;
    boolean removeFeed(Long feedNumber) throws Exception;
}
