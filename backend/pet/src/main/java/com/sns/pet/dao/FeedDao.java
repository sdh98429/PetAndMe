package com.sns.pet.dao;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedDao {
    List<FeedDto> selectMyFeedList(Long userNumber) throws Exception;

    int insertFeed(FeedDto feedDto) throws Exception;

    int insertImages(FeedDto feedDto) throws Exception;

    FeedDto selectFeed(Long feedNumber) throws Exception;
    List<FeedPhotoDto> selectFeedPhotoByFeedNumber(Long feedNumber) throws Exception;
//    int updateFeed(FeedDto feedDto) throws Exception;
    int deleteFeed(Long feedNumber) throws Exception;
    int deletePhotos(Long feedNumber) throws Exception;
}
