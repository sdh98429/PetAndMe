package com.sns.pet.service;

import com.sns.pet.dao.LoginDao;
import com.sns.pet.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final SqlSession sqlSession;

    @Override
    public UserDto findByIdAndPw(Map<String, String> user) throws Exception {
        String userID = user.get("userID");
        String userPW = user.get("userPW");
        if(userID == null || userPW == null) {
            return null;
        }
        return sqlSession.getMapper(LoginDao.class).selectByIdAndPw(user);
    }

    @Override
    public UserDto findById(String userID) throws Exception {
        return sqlSession.getMapper(LoginDao.class).selectById(userID);
    }
}
