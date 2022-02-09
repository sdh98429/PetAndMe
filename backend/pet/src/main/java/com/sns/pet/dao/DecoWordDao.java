package com.sns.pet.dao;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface DecoWordDao {
    // 동물이름 한국명 조회
    String selectKoreanName(String animalName_en) throws SQLException;
    // 랜덤으로 꾸미는 말 조회
    String selectDecoWord() throws SQLException;
}
