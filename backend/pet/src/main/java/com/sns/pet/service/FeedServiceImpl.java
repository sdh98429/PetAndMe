package com.sns.pet.service;

import com.sns.pet.dao.FeedDao;
import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final SqlSession sqlSession;

    @Override
    public List<FeedDto> findNewsFeedList(Long userNumber, String cursor) throws SQLException {
        return sqlSession.getMapper(FeedDao.class).selectNewsFeedList(userNumber, cursor);
    }

    @Override
    public List<FeedDto> findSimilarAnimalFeedList(Long userNumber, int animalNumber, String cursor) throws SQLException {
        return sqlSession.getMapper(FeedDao.class).selectSimilarAnimalFeedList(userNumber, animalNumber, cursor);
    }

    @Override
    public List<FeedDto> findFollowFeedList(Long userNumber, String cursor) throws SQLException {
        return sqlSession.getMapper(FeedDao.class).selectFollowList(userNumber, cursor);
    }


    @Override
    public List<FeedDto> findMyFeedList(Long userNumber) throws SQLException {
        return sqlSession.getMapper(FeedDao.class).selectMyFeedList(userNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFeed(FeedDto feedDto) throws SQLException {
        if (sqlSession.getMapper(FeedDao.class).insertFeed(feedDto) == 1) {
            if (feedDto.getFeedPhotoDtoList() != null)
                return sqlSession.getMapper(FeedDao.class).insertImages(feedDto) == feedDto.getFeedPhotoDtoList().size();
            else
                return true;
        } else {
            return false;
        }
    }

    @Override
    public FeedDto findFeed(Long userNumber, Long feedNumber) throws SQLException {
        return sqlSession.getMapper(FeedDao.class).selectFeed(userNumber, feedNumber);
    }

//    @Override
//    public boolean modifyFeed(FeedDto feedDto) throws Exception {
//        return sqlSession.getMapper(FeedDao.class).updateFeed(feedDto) == 1;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeFeed(Long feedNumber) throws SQLException {
        List<FeedPhotoDto> FeedPhotoDtoList = sqlSession.getMapper(FeedDao.class).selectFeedPhotoByFeedNumber(feedNumber);
        if (sqlSession.getMapper(FeedDao.class).deletePhotos(feedNumber) == FeedPhotoDtoList.size() && sqlSession.getMapper(FeedDao.class).deleteFeed(feedNumber) == 1) {
            // 파일 삭제
            for (FeedPhotoDto feedPhotoDto : FeedPhotoDtoList) {
                try {
                    Files.delete(Paths.get(feedPhotoDto.getSaveFolder() + feedPhotoDto.getPhotoName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public List<FeedPhotoDto> listImage(FeedDto feedNumbers) throws SQLException{
        return sqlSession.getMapper(FeedDao.class).selectFeedPhotoList(feedNumbers);
    }


}
