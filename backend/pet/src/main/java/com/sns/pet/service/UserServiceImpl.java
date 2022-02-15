package com.sns.pet.service;

import com.sns.pet.dao.EmailDao;
import com.sns.pet.dao.FavAnimalDao;
import com.sns.pet.dao.PetDao;
import com.sns.pet.dao.UserDao;
import com.sns.pet.dto.JoinDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final SqlSession sqlSession;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(JoinDto joinDto) throws Exception {
        sqlSession.getMapper(UserDao.class).insertUser(joinDto);
        sqlSession.getMapper(EmailDao.class).updateAuthKey(joinDto.getUserEmail());
        System.out.println(joinDto.toString());
        if (joinDto.getUserPreference() != null) {
            for (int i = 0; i < joinDto.getUserPreference().size(); i++) {
                joinDto.getUserPreference().get(i).setUserNumber(joinDto.getUserNumber());
            }
            sqlSession.getMapper(FavAnimalDao.class).insertFavAnimal(joinDto.getUserPreference());
        }
        if (joinDto.isPetCheck()) {
            for (int i = 0; i < joinDto.getUserPet().size(); i++) {
                joinDto.getUserPet().get(i).setUserNumber(joinDto.getUserNumber());
            }
            return sqlSession.getMapper(PetDao.class).insertJoinPet(joinDto.getUserPet()) == joinDto.getUserPet().size();
        } else {
            return true;
        }
    }

    @Override
    public boolean checkUserID(String userID) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUserID(userID) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyUser(JoinDto joinDto) throws Exception {
        sqlSession.getMapper(UserDao.class).updateUser(joinDto);
        System.out.println(joinDto.toString());
        if (joinDto.getUserPreference() != null) {
            sqlSession.getMapper(FavAnimalDao.class).deleteFavAnimal(joinDto.getUserNumber());
            for (int i = 0; i < joinDto.getUserPreference().size(); i++) {
                joinDto.getUserPreference().get(i).setUserNumber(joinDto.getUserNumber());
            }
            sqlSession.getMapper(FavAnimalDao.class).insertFavAnimal(joinDto.getUserPreference());
        }
        if (joinDto.isPetCheck()) {
            for (int i = 0; i < joinDto.getUserPet().size(); i++) {
                joinDto.getUserPet().get(i).setUserNumber(joinDto.getUserNumber());
            }
            return sqlSession.getMapper(PetDao.class).updatePet(joinDto.getUserPet()) == joinDto.getUserPet().size();
        } else {
            return true;
        }
    }

    @Override
    public boolean modifyUserNickName(Map<String, Object> user) throws Exception {
        return sqlSession.getMapper(UserDao.class).updateUserNickName(user) == 1;
    }

    @Override
    public UserDto findUser(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUser(userNumber);
    }


    @Override
    public boolean removeUser(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).deleteUser(userNumber) == 1;
    }

    @Override
    public boolean modifyUserPhoto(UserDto userDto) throws Exception {
        return sqlSession.getMapper(UserDao.class).updateUserPhoto(userDto) == 1;
    }

    @Override
    public UserPetDto findUserInfo(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUserInfo(userNumber);
    }

    @Override
    public UserPetDto findNoPetUserInfo(Long userNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectNoPetUserInfo(userNumber);
    }

    @Override
    public UserDto findUserNumber(String userID) throws Exception {
        return sqlSession.getMapper(UserDao.class).selectUserNumber(userID);
    }

    @Override
    public boolean addFollow(Long fromUserNumber, Long toUserNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).insertFollow(fromUserNumber, toUserNumber) == 1;
    }

    @Override
    public boolean removeFollow(Long fromUserNumber, Long toUserNumber) throws Exception {
        return sqlSession.getMapper(UserDao.class).deleteFollow(fromUserNumber, toUserNumber) == 1;
    }

    @Override
    public List<UserDto> findFollowList(Long userNumber) throws SQLException {
        return sqlSession.getMapper(UserDao.class).selectFollowList(userNumber);
    }

    @Override
    public List<UserDto> findFollowingList(Long userNumber) throws SQLException {
        return sqlSession.getMapper(UserDao.class).selectFollowingList(userNumber);
    }
}

