package com.sns.pet.dao;

import com.sns.pet.dto.PetDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PetDao {
    // 모든 반려동물 목록 조회
    List<PetDto> selectAllPets(@Param("userNumber") Long userNumber) throws SQLException;
    // 특정 반려동물 상세조회
    PetDto selectPet(@Param("userNumber") Long userNumber, @Param("petNumber") Long petNumber) throws SQLException;
    // 유저의 반려동물 이름 중복 체크
    int selectPetName(@Param("userNumber") Long userNumber, @Param("petName") String petName) throws SQLException;
    // 반려동물 정보 저장
    int insertPet(PetDto petDto) throws SQLException;
    // 반려동물 정보 삭제
    int deletePet(@Param("userNumber") Long userNumber, @Param("petNumber") Long petNumber) throws SQLException;
    // 회원가입 반려동물 정보 저장
    int insertJoinPet(List<PetDto> petDtoList) throws SQLException;
    // 반려동물 정보 수정
    int updatePet(List<PetDto> petDtoList) throws SQLException;
}
