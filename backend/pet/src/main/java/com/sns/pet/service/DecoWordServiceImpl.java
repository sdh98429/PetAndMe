package com.sns.pet.service;

import com.sns.pet.dao.DecoWordDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecoWordServiceImpl implements DecoWordService {

    private final SqlSession sqlSession;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> makeNickName(int animalNumber) throws Exception {
        String name = sqlSession.getMapper(DecoWordDao.class).selectAnimalName(animalNumber);
        List<String> deco = sqlSession.getMapper(DecoWordDao.class).selectDecoWord();

        List<String> nickName = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nickName.add(deco.get(i).concat(" ").concat(name));
        }
        return nickName;
    }
}