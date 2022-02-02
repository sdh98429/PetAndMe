package com.sns.pet.service;

import com.sns.pet.dto.petDto;
import com.sns.pet.dao.petDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private static petDao petDao;
    private static PetService petService = new PetServiceImpl(petDao);

    @Autowired
    public PetServiceImpl(petDao petMapper) {
        this.petDao = petMapper;
    }

    @Override
    public petDto findPet(Long userNumber, int petNumber) throws Exception {
        return petDao.selectPet(userNumber, petNumber);
    }

    @Override
    public boolean addPet(petDto petDto) throws Exception {
        return petDao.insertPet(petDto) == 1;
    }

    @Override
    public boolean modifyPet(petDto petDto) throws Exception {
        return petDao.updatePet(petDto) == 1;
    }

    @Override
    public boolean removePet(Long userNumber, int petNumber) throws Exception {
        return petDao.deletePet(userNumber, petNumber) == 1;
    }
}
