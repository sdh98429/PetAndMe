package com.sns.pet.service;

import com.sns.pet.dto.PetDto;
import com.sns.pet.dao.PetDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final SqlSession sqlSession;

    @Override
    public List<PetDto> findAllPets(Long userNumber) throws Exception {
        return sqlSession.getMapper(PetDao.class).selectAllPets(userNumber);
    }

    @Override
    public PetDto findPet(Long userNumber, Long petNumber) throws Exception {
        return sqlSession.getMapper(PetDao.class).selectPet(userNumber, petNumber);
    }

    @Override
    public boolean checkPetName(Long userNumber, String petName) throws Exception {
        // 중복이 아닌 경우 = 0
        return sqlSession.getMapper(PetDao.class).selectPetName(userNumber, petName) == 0;
    }

    @Override
    public boolean addPet(PetDto petDto) throws Exception {
        return sqlSession.getMapper(PetDao.class).insertPet(petDto) == 1;
    }

    @Override
    public boolean removePet(Long userNumber, Long petNumber) throws Exception {
        return sqlSession.getMapper(PetDao.class).deletePet(userNumber, petNumber) == 1;
    }
}
