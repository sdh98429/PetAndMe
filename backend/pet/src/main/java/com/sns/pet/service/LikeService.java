package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

import java.util.List;

public interface LikeService {
    // 좋아요 생성
    boolean addLike(Long userNumber, Long feedNumber) throws Exception;
    // 좋아요 삭제
    boolean removeLike(Long userNumber, Long feedNumber) throws Exception;
    // 해당 피드 좋아요 조회
    List<UserDto> findLikeList(Long feedNumber) throws Exception;
}
