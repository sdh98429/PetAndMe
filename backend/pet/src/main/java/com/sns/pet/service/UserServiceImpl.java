package com.sns.pet.service;

import com.sns.pet.dao.UserDao;
import com.sns.pet.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean join(UserDto userDto) throws Exception {
        if(userDto == null){
            throw new Exception();
        }
        return sqlSession.getMapper(UserDao.class).join(userDto) == 1;
    }

    @Override
    public UserDto userInfo(long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).userInfo(userNumber);
    }

    @Override
    public boolean userModify(UserDto userDto) throws Exception {
        return sqlSession.getMapper(UserDao.class).userModify(userDto) == 1;
    }

    @Override
    public boolean userRemove(long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).userRemove(userNumber) == 1;
    }
}
