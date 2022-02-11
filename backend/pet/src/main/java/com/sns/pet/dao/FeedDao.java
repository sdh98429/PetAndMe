package com.sns.pet.dao;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FeedDao {
    List<FeedDto> selectNewsFeedList(Long userNumber, String cursor) throws SQLException;

    List<FeedDto> selectSimilarAnimalFeedList(Long userNumber, int animalNumber, String cursor) throws SQLException;

    List<FeedDto> selectFollowList(Long userNumber, String cursor) throws SQLException;

    List<FeedDto> selectMyFeedList(Long userNumber) throws SQLException;

    int insertFeed(FeedDto feedDto) throws SQLException;

    int insertImages(FeedDto feedDto) throws SQLException;

    FeedDto selectFeed(Long userNumber, Long feedNumber) throws SQLException;

    List<FeedPhotoDto> selectFeedPhotoByFeedNumber(Long feedNumber) throws SQLException;

    //    int updateFeed(FeedDto feedDto) throws SQLException;
    int deleteFeed(Long feedNumber) throws SQLException;

    int deletePhotos(Long feedNumber) throws SQLException;

    List<FeedPhotoDto> selectFeedPhotoList(FeedDto feedNumbers) throws SQLException;
}
