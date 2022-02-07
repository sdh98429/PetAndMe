package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

import java.util.List;

public interface LikeService {
    boolean addLike(Long userNumber, Long feedNumber) throws Exception;
    boolean removeLike(Long userNumber, Long feedNumber) throws Exception;
    List<UserDto> findLikeList(Long feedNumber) throws Exception;
}
