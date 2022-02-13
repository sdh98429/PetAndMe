package com.sns.pet.dao;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FeedDao {
    // 뉴스피드 조회(선호동물 피드 + 친구 피드)
    List<FeedDto> selectNewsFeedList(Long userNumber, String cursor) throws SQLException;
    // 닮은 동물 피드 조회
    List<FeedDto> selectSimilarAnimalFeedList(Long userNumber, int animalNumber, String cursor) throws SQLException;
    // 친구피드 조회
    List<FeedDto> selectFollowList(Long userNumber, String cursor) throws SQLException;
    // 내 피드 목록 조회
    List<FeedDto> selectMyFeedList(Long userNumber) throws SQLException;
    // 피드 생성
    int insertFeed(FeedDto feedDto) throws SQLException;
    // 피드 이미지 생성
    int insertImages(FeedDto feedDto) throws SQLException;
    // 해당 피드 조회
    FeedDto selectFeed(Long userNumber, Long feedNumber) throws SQLException;
    // 피드 번호에 해당하는 이미지 조회
    List<FeedPhotoDto> selectFeedPhotoByFeedNumber(Long feedNumber) throws SQLException;
    // 피드 수정
    //    int updateFeed(FeedDto feedDto) throws SQLException;
    // 피드 삭제
    int deleteFeed(Long feedNumber) throws SQLException;
    // 피드 이미지 삭제
    int deletePhotos(Long feedNumber) throws SQLException;
    // 피드 번호에 해당하는 이미지 조회(Taping)
    List<FeedPhotoDto> selectFeedPhotoList(FeedDto feedNumbers) throws SQLException;
}
