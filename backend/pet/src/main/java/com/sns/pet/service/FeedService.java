package com.sns.pet.service;

import com.sns.pet.dto.CommentDto;
import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;

import java.sql.SQLException;
import java.util.List;

public interface FeedService {
    // 뉴스피드 조회(선호동물 피드 + 친구 피드)
    List<FeedDto> findNewsFeedList(Long userNumber, String cursor) throws SQLException;
    // 닮은 동물 피드 조회
    List<FeedDto> findSimilarAnimalFeedList(Long userNumber, int animalNumber, String cursor) throws SQLException;
    // 친구피드 조회
    List<FeedDto> findFollowFeedList(Long userNumber, String cursor) throws SQLException;
    // 내 피드 목록 조회
    List<FeedDto> findMyFeedList(Long userNumber) throws SQLException;
    // 피드 생성
    boolean addFeed(FeedDto feedDto) throws SQLException;
    // 해당 피드 조회
    FeedDto findFeed(Long userNumber, Long feedNumber) throws SQLException;
    // 피드 수정
//    boolean modifyFeed(FeedDto feedDto) throws SQLException;
    // 피드 삭제
    boolean removeFeed(Long feedNumber) throws SQLException;
    // 피드 번호에 해당하는 이미지 조회(Taping)
    List<FeedPhotoDto> listImage(FeedDto feedNumbers) throws SQLException;
}
