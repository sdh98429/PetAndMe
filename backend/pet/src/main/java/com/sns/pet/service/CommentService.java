package com.sns.pet.service;

import com.sns.pet.dto.CommentDto;

import java.util.List;

public interface CommentService {
    boolean addComment(CommentDto commentDto) throws Exception;
    boolean deleteComment(Long commentNumber) throws Exception;
    List<CommentDto> findCommentList(Long feedNumber) throws Exception;
}
