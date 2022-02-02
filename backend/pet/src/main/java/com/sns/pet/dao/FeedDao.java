package com.sns.pet.dao;

import com.sns.pet.dto.FeedDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedDao {
        List<FeedDto> selectMyFeedList(Long userNumber) throws Exception;
    int insertFeed(FeedDto feedDto) throws Exception;
    int insertImages(FeedDto feedDto) throws Exception;
    public FeedDto selectFeed(Long feedNumber) throws Exception;
//    public int updateFeed(FeedDto feedDto) throws Exception;
//    public int deleteFeed(int feedNumber) throws Exception;
}
