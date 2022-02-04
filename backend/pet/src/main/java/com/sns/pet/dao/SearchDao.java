package com.sns.pet.dao;

import com.sns.pet.dto.SearchDto;
import com.sns.pet.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SearchDao {

    // 최근 검색 기록 10개 가져오기
    List<String> searchPastKeyword(Long userNumber) throws SQLException;
    // 유저 관련 연관 검색어 가져오기
    List<UserDto> searchKeywordAboutUser(String searchWord) throws SQLException;
//    // 태그 관련 연관 검색어 가져오기
//    List<String> searchKeywordAboutTag(String searchWord) throws SQLException;
//    // 태그 검색 결과 모두 가져오기
//    List<SearchDto> searchResultTag(String searchWord) throws SQLException;
    // 검색어 저장(이미 검색한 단어라면 날짜 갱신)
    int insertKeyword(SearchDto searchDto) throws SQLException;
}
