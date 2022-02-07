package com.sns.pet.controller;

import com.sns.pet.dto.SearchDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 실시간 userID관련 연관 검색어 조회
    @ApiOperation(value = "실시간 userID관련 연관 검색어 조회", notes = "회원의 검색 내용이 userID와 관련된 연관 유저가 나옵니다.")
    @GetMapping("/rt/userid/{searchWord}")
    public ResponseEntity<List<UserDto>> searchUserIDList(
            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {

        logger.info("searchUserIDList 호출");
        return new ResponseEntity<List<UserDto>>(searchService.findUserKeywordByUserID(searchWord), HttpStatus.OK);
    }

    // 실시간 NickName관련 연관 검색어 조회
    @ApiOperation(value = "실시간 NickName관련 연관 검색어 조회", notes = "회원의 검색 내용이 NickName과 관련된 연관 유저가 나옵니다.")
    @GetMapping("/rt/userName/{searchWord}")
    public ResponseEntity<List<UserDto>> searchUserNameList(
            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {

        logger.info("searchUserNameList 호출");
        return new ResponseEntity<List<UserDto>>(searchService.findUserKeywordByName(searchWord), HttpStatus.OK);
    }

//    // 실시간 태그 연관 검색어 조회
//    @ApiOperation(value = "실시간 태그관련 연관 검색어 조회", notes = "회원의 검색 내용이 태그관련이면 연관 유저가 나옵니다.")
//    @GetMapping("/rt/hashtag/{searchWord}")
//    public ResponseEntity<List<String>> searchTagList(
//            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {
//
//        logger.info("searchTagList 호출");
//        return new ResponseEntity<List<String>>(searchService.findSearchAboutTag(searchWord), HttpStatus.OK);
//    }
//
//    // 태그 검색에 따른 결과 피드 목록 조회
//    @ApiOperation(value = "태그관련 검색에 따른 결과 피드 모두 조회", notes = "태그 관련으로 검색 시 관련된 피드가 모두 나옵니다.")
//    @GetMapping("/hashtag/{searchWord}")
//    public ResponseEntity<List<SearchDto>> searchResultTagList(
//            @ApiParam(value = "검색어") @PathVariable("searchWord") String searchWord) throws Exception {
//
//        logger.info("searchResultTagList 호출");
//        return new ResponseEntity<List<SearchDto>>(searchService.findResultTag(searchWord), HttpStatus.OK);
//    }

    // 검색어 저장(이전 검색 기록이 있다면 검색날짜 갱신)
    @ApiOperation(value = "검색 기록 저장 및 검색날짜 갱신", notes = "검색어가 저장되며, 과거 검색한 적이 있다면 검색날짜가 갱신됩니다.")
    @PostMapping
    public ResponseEntity<String> searchAdd(
            @ApiParam(value = "검색 객체", required = true) SearchDto searchDto) throws Exception {

        logger.info("searchAdd 호출");
        searchService.addSearch(searchDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
