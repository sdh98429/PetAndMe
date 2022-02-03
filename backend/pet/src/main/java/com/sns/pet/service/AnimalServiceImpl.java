package com.sns.pet.service;

import com.sns.pet.dao.AnimalDao;
import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.AnimalLikeDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService{

    private final SqlSession sqlSession;

    @Override
    public boolean addAnimal(AnimalLikeDto animalLikeDto) throws Exception {
        if(animalLikeDto == null){
            throw new Exception();
        }
        return sqlSession.getMapper(AnimalDao.class).insertAnimal(animalLikeDto) == 1;
    }

    @Override
    @Transactional
    public boolean modifyAnimal(AnimalLikeDto animalLikeDto) throws Exception {
        //sqlSession.getMapper(AnimalDao.class).deleteAnimal(userNumber);


        return true;
    }

    @Override
    public boolean removeAnimal(Long userNumber) throws Exception {
        sqlSession.getMapper(AnimalDao.class).deleteAnimal(userNumber);
        return true;
    }

    @Override
    public List<AnimalLikeDto> findFavAnimal(Long userNumber) throws Exception {
        return sqlSession.getMapper(AnimalDao.class).selectFavAnimal(userNumber);
    }

    @Override
    public List<AnimalDto> findAnimal() throws Exception {
        return sqlSession.getMapper(AnimalDao.class).selectAnimal();
    }
}
