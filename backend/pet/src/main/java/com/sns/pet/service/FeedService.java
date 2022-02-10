package com.sns.pet.service;

import com.sns.pet.dto.CommentDto;
import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;

import java.sql.SQLException;
import java.util.List;

public interface FeedService {
    List<FeedDto> findNewsFeedList(Long userNumber, String cursor) throws SQLException;
    List<FeedDto> findFavFeedList(Long userNumber, String cursor) throws SQLException;
    List<FeedDto> findFollowFeedList(Long userNumber, String cursor) throws SQLException;
    List<FeedDto> findMyFeedList(Long userNumber) throws SQLException;
    boolean addFeed(FeedDto feedDto) throws SQLException;
    FeedDto findFeed(Long userNumber, Long feedNumber) throws SQLException;
//    boolean modifyFeed(FeedDto feedDto) throws SQLException;
    boolean removeFeed(Long feedNumber) throws SQLException;
    List<FeedPhotoDto> listImage(int[] feedNumbers) throws SQLException;
}
