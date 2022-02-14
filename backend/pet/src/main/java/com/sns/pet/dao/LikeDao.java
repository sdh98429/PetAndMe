package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;
import org.mapstruct.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface LikeDao {
    // 좋아요 생성
    int insertLike(Long userNumber, Long feedNumber) throws SQLException;
    // 좋아요 삭제
    int deleteLike(Long userNumber, Long feedNumber) throws SQLException;
    // 해당 피드 좋아요 조회
    List<UserDto> selectLikeList(Long feedNumber) throws SQLException;
}
