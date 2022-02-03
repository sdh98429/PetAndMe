package com.sns.pet.service;

import com.sns.pet.dao.UserDao;
import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final SqlSession sqlSession;

    @Override
    public boolean addUser(UserDto userDto) throws Exception {
        if(userDto == null){
            throw new Exception();
        }
        return sqlSession.getMapper(UserDao.class).insertUser(userDto) == 1;
    }

    @Override
    public UserDto findUser(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUser(userNumber);
    }

    @Override
    public boolean modifyUser(UserDto userDto) throws Exception {
        return sqlSession.getMapper(UserDao.class).updateUser(userDto) == 1;
    }

    @Override
    public boolean removeUser(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).deleteUser(userNumber) == 1;
    }

    @Override
    public UserPetDto findUserInfo(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUserInfo(userNumber);
    }

}
