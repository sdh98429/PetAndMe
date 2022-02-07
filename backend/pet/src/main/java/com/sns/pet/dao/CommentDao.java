package com.sns.pet.dao;

import com.sns.pet.dto.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    int insertComment(CommentDto commentDto) throws Exception;
    List<CommentDto> selectCommentList(Long FeedNumber) throws Exception;
    int deleteComment(Long commentNumber) throws Exception;
}
