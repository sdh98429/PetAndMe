package com.sns.pet.service;

import com.sns.pet.dto.SearchDto;
import com.sns.pet.dto.UserDto;

import java.util.List;

public interface SearchService {

    // 최근 검색 기록 10개 가져오기
    List<String> findPastSearch(Long userNumber) throws Exception;
    // 유저 관련 연관 검색어 userID로 조회
    List<UserDto> findUserKeywordByUserID(String searchWord) throws Exception;
    // 유저 관련 연관 검색어 NickName으로 조회
    List<UserDto> findUserKeywordByName(String searchWord) throws Exception;
    //    // 태그 관련 연관 검색어 가져오기
//    List<String> findSearchAboutTag(String searchWord) throws Exception;
//    // 태그 검색 결과 모두 가져오기
//    List<SearchDto> findResultTag(String searchWord) throws Exception;
    // 검색어 저장(이전 검색 기록이 있다면 검색날짜 갱신)
    boolean addSearch(SearchDto searchDto) throws Exception;
}
