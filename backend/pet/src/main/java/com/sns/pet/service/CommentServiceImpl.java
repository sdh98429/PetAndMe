package com.sns.pet.service;

import com.sns.pet.dao.CommentDao;
import com.sns.pet.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final SqlSession sqlSession;

    @Override
    public boolean addComment(CommentDto commentDto) throws Exception {
        return sqlSession.getMapper(CommentDao.class).insertComment(commentDto) == 1;
    }

    @Override
    public boolean deleteComment(Long commentNumber) throws Exception {
        return sqlSession.getMapper(CommentDao.class).deleteComment(commentNumber) == 1;
    }

    @Override
    public List<CommentDto> findCommentList(Long feedNumber) throws Exception {
        return sqlSession.getMapper(CommentDao.class).selectCommentList(feedNumber);
    }


}
