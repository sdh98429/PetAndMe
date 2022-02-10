package com.sns.pet.dao;

import com.sns.pet.dto.CommentDto;
import org.mapstruct.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentDao {
    int insertComment(CommentDto commentDto) throws SQLException;
    List<CommentDto> selectCommentList(Long FeedNumber) throws SQLException;
    int deleteComment(Long commentNumber) throws SQLException;
}
