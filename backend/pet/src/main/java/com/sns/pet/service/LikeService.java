package com.sns.pet.service;

public interface LikeService {
    boolean addLike(Long userNumber, Long feedNumber) throws Exception;
    boolean removeLike(Long userNumber, Long feedNumber) throws Exception;
    boolean findLikeList(Long userNumber, Long feedNumber) throws Exception;
}
