package com.sns.pet.controller;

import com.sns.pet.dto.FeedPhotoDto;
import com.sns.pet.dto.SearchDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class SearchController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PetController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final SearchService searchService;

    // 최근 검색 기록 최대 10개 조회
    @ApiOperation(value = "최근 검색 기록 최대 10개 조회", notes = "회원이 검색을 시도하려 할 때 최근 검색 기록이 나옵니다.")
    @GetMapping("/{userNumber}")
    public ResponseEntity<List<String>> searchList(
            @ApiParam(value = "유저번호", required = true) @PathVariable("userNumber") Long userNumber) throws Exception {

        logger.info("searchList 호출");
        return new ResponseEntity<List<String>>(searchService.findPastSearch(userNumber), HttpStatus.OK);
    }

    // 실시간 userID관련 연관 검색어/자동 완성 조회
    @ApiOperation(value = "실시간 userID관련 연관 검색어 조회", notes = "회원의 검색 내용이 userID와 관련된 연관 유저가 나옵니다.")
    @GetMapping("/rt/userid/{searchWord}")
    public ResponseEntity<List<UserDto>> searchUserIDList(
            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {

        logger.info("searchUserIDList 호출");
        List<UserDto> userDtoList = searchService.findUserKeywordByUserID(searchWord);

        // 이미지 변환
        InputStream imageStream;
        for (UserDto userDto : userDtoList) {
            imageStream = new FileInputStream(userDto.getSaveFolder() + userDto.getUserPhotoName());
            userDto.setUserProfilePhoto(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 실시간 NickName관련 연관 검색어/자동 완성 조회
    @ApiOperation(value = "실시간 NickName관련 연관 검색어 조회", notes = "회원의 검색 내용이 NickName과 관련된 연관 유저가 나옵니다.")
    @GetMapping("/rt/userName/{searchWord}")
    public ResponseEntity<List<UserDto>> searchUserNameList(
            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {

        logger.info("searchUserNameList 호출");
        List<UserDto> userDtoList = searchService.findUserKeywordByName(searchWord);

        // 이미지 변환
        InputStream imageStream;
        for (UserDto userDto : userDtoList) {
            imageStream = new FileInputStream(userDto.getSaveFolder() + userDto.getUserPhotoName());
            userDto.setUserProfilePhoto(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 동물 종으로 검색 시, 해당 동물을 펫으로 하는 유저 조회
    @ApiOperation(value = "동물 종으로 검색 시, 해당 동물을 반려동물로 키우는 유저 조회", notes = "한국명 동물 이름을 검색하여 해당 동물을 키우고 있는 유저를 조회한다.")
    @GetMapping("/animal/{searchWord}")
    public ResponseEntity<List<UserDto>> searchUserListByAnimal(
            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {

        logger.info("searchUserListByAnimal 호출");
        List<UserDto> userDtoList = searchService.findUserByAnimalName(searchWord);

        // 이미지 변환
        InputStream imageStream;
        for (UserDto userDto : userDtoList) {
            imageStream = new FileInputStream(userDto.getSaveFolder() + userDto.getUserPhotoName());
            userDto.setUserProfilePhoto(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 검색어 저장(이전 검색 기록이 있다면 검색날짜 갱신)
    @ApiOperation(value = "검색 기록 저장 및 검색날짜 갱신", notes = "검색어가 저장되며, 과거 검색한 적이 있다면 검색날짜가 갱신됩니다.")
    @PostMapping
    public ResponseEntity<String> searchAdd(
            @ApiParam(value = "검색 객체", required = true) @RequestBody SearchDto searchDto) throws Exception {

        logger.info("searchAdd 호출");
        logger.info("searchDto {}, {}", searchDto.getUserNumber(), searchDto.getSearchWord());
        if(searchService.addSearch(searchDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        // 잘못된 요청을 보냄
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST); // 400
    }
}
