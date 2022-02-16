package com.sns.pet.dao;

import com.sns.pet.dto.CommentDto;
import org.mapstruct.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentDao {
    // 댓글 생성
    int insertComment(CommentDto commentDto) throws SQLException;
    // 피드 번호에 해당하는 댓글 조회
    List<CommentDto> selectCommentList(Long FeedNumber) throws SQLException;
    // 해당 댓글 번호 삭제
    int deleteComment(Long commentNumber) throws SQLException;
}
