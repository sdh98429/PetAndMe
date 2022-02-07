package com.sns.pet.dao;

import org.mapstruct.Mapper;

@Mapper
public interface LikeDao {
    int insertLike(Long userNumber, Long feedNumber);
    int deleteLike(Long userNumber, Long feedNumber);
}
