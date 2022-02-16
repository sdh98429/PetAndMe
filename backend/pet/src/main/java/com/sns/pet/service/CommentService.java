package com.sns.pet.service;

import com.sns.pet.dto.CommentDto;

import java.util.List;

public interface CommentService {
    // 댓글 생성
    boolean addComment(CommentDto commentDto) throws Exception;
    // 댓글 삭제
    boolean deleteComment(Long commentNumber) throws Exception;
    // 피드 번호에 해당하는 댓글 조회
    List<CommentDto> findCommentList(Long feedNumber) throws Exception;
}
