package com.sns.pet.service;

import com.sns.pet.dao.FavAnimalDao;
import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.FavAnimalDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FavAnimalServiceImpl implements FavAnimalService {

    private final SqlSession sqlSession;

    @Override
    public boolean addFavAnimal(List<FavAnimalDto> favAnimalDtoList) throws Exception {
        if(favAnimalDtoList == null){
            throw new Exception();
        }
        return sqlSession.getMapper(FavAnimalDao.class).insertFavAnimal(favAnimalDtoList) == favAnimalDtoList.size();
    }

    @Override
    @Transactional
    public boolean modifyFavAnimal(List<FavAnimalDto> favAnimalDtoList) throws Exception {
        sqlSession.getMapper(FavAnimalDao.class).deleteFavAnimal(favAnimalDtoList.get(0).getUserNumber());
        return sqlSession.getMapper(FavAnimalDao.class).insertFavAnimal(favAnimalDtoList) == favAnimalDtoList.size();
    }

    @Override
    public boolean removeFavAnimal(Long userNumber) throws Exception {
        sqlSession.getMapper(FavAnimalDao.class).deleteFavAnimal(userNumber);
        return true;
    }

    @Override
    public List<FavAnimalDto> findFavAnimal(Long userNumber) throws Exception {
        return sqlSession.getMapper(FavAnimalDao.class).selectFavAnimal(userNumber);
    }

    @Override
    public List<AnimalDto> findAnimal() throws Exception {
        return sqlSession.getMapper(FavAnimalDao.class).selectAnimal();
    }
}
