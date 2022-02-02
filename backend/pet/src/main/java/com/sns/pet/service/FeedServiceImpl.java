package com.sns.pet.service;

import com.sns.pet.dao.FeedDao;
import com.sns.pet.dto.FeedDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{

    private final SqlSession sqlSession;

    @Override
    public List<FeedDto> findMyFeedList(Long userNumber) throws Exception {
        return sqlSession.getMapper(FeedDao.class).selectMyFeedList(userNumber);
    }

    @Override
    @Transactional
    public boolean addFeed(FeedDto feedDto) throws Exception {
        if(sqlSession.getMapper(FeedDao.class).insertFeed(feedDto) == 1) {
            if(feedDto.getFileInfoDtoList() != null)
                return sqlSession.getMapper(FeedDao.class).insertImages(feedDto) == feedDto.getFileInfoDtoList().size();
            else
                return true;
        } else {
            return false;
        }
    }

    @Override
    public FeedDto findFeed(Long feedNumber) throws Exception {
        return sqlSession.getMapper(FeedDao.class).selectFeed(feedNumber);
    }

//    @Override
//    public boolean feedModify(FeedDto feedDto) throws Exception {
//        return feedDao.updateFeed(feedDto);
//    }
//
//    @Override
//    public boolean feedRemove(int feedNumber) throws Exception {
//        return feedDao.deleteFeed(feedNumber);
//    }
}
