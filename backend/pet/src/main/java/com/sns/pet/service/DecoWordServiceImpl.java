package com.sns.pet.service;

import com.sns.pet.dao.DecoWordDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DecoWordServiceImpl implements DecoWordService {

    private final SqlSession sqlSession;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String makeNickName(String animalName_en) throws Exception {
        String name = sqlSession.getMapper(DecoWordDao.class).selectKoreanName(animalName_en);
        String deco = sqlSession.getMapper(DecoWordDao.class).selectDecoWord();
        return deco.concat(" ").concat(name);
    }
}