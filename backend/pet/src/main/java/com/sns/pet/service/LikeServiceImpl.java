package com.sns.pet.service;

import com.sns.pet.dao.LikeDao;
import com.sns.pet.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final SqlSession sqlSession;

    @Override
    public boolean addLike(Long userNumber, Long feedNumber) throws Exception {
        return sqlSession.getMapper(LikeDao.class).insertLike(userNumber, feedNumber) == 1;
    }

    @Override
    public boolean removeLike(Long userNumber, Long feedNumber) throws Exception {
        return sqlSession.getMapper(LikeDao.class).deleteLike(userNumber, feedNumber) == 1;
    }

    @Override
    public List<UserDto> findLikeList(Long feedNumber) throws Exception {
        return sqlSession.getMapper(LikeDao.class).selectLikeList(feedNumber);
    }


}
