package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;
import org.mapstruct.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface LikeDao {
    int insertLike(Long userNumber, Long feedNumber) throws SQLException;
    int deleteLike(Long userNumber, Long feedNumber) throws SQLException;
    List<UserDto> selectLikeList(Long feedNumber) throws SQLException;
}
