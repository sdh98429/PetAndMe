package com.sns.pet.service;

import com.sns.pet.dao.FeedDao;
import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final SqlSession sqlSession;

    @Override
    public List<FeedDto> findMyFeedList(Long userNumber) throws Exception {
        return sqlSession.getMapper(FeedDao.class).selectMyFeedList(userNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFeed(FeedDto feedDto) throws Exception {
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
    public FeedDto findFeed(Long feedNumber) throws Exception {
        return sqlSession.getMapper(FeedDao.class).selectFeed(feedNumber);
    }

//    @Override
//    public boolean modifyFeed(FeedDto feedDto) throws Exception {
//        return sqlSession.getMapper(FeedDao.class).updateFeed(feedDto) == 1;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeFeed(Long feedNumber) throws Exception {
        List<FeedPhotoDto> fileInfoDtoList = sqlSession.getMapper(FeedDao.class).selectFeedPhotoByFeedNumber(feedNumber);
        sqlSession.getMapper(FeedDao.class).deletePhotos(feedNumber);
        sqlSession.getMapper(FeedDao.class).deleteFeed(feedNumber);

        // 파일 삭제
        for (FeedPhotoDto feedPhotoDto : fileInfoDtoList) {
            Files.delete(Paths.get(feedPhotoDto.getSaveFolder() + feedPhotoDto.getPhotoName()));
        }
        return true;
    }
}
