package com.sns.pet.dao;

import com.sns.pet.dto.SearchDto;
import com.sns.pet.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SearchDao {

    // 최근 검색 기록 10개 가져오기
    List<String> selectPastKeyword(Long userNumber) throws SQLException;
    // 유저 관련 연관 검색어/자동 완성 userID로 조회
    List<UserDto> selectUserKeywordByUserID(String searchWord) throws SQLException;
    // 유저 관련 연관 검색어/자동 완성 NickName으로 조회
    List<UserDto> selectUserKeywordByName(String searchWord) throws SQLException;
    // 동물 종(animalName)으로 검색 시, 해당 동물을 펫으로 하는 유저 조회
    List<UserDto> selectUserByAnimalName(String searchWord) throws SQLException;
    // 검색어 저장(이미 검색한 단어라면 날짜 갱신)
    int insertKeyword(SearchDto searchDto) throws SQLException;
}
