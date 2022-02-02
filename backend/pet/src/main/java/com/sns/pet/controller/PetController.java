package com.sns.pet.controller;

import com.sns.pet.dto.petDto;
import com.sns.pet.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/pet")
public class PetController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PetController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private PetService petService;

    // 반려동물 리스트(전체 정보) 조회 - 등록한 반려동물이 여러마리인 경우
//    @GetMapping
//    public void petList() {
//
//    }

    // 특정 반려동물 상세정보 조회
    @ApiOperation(value = "반려동물 상세정보 조회", notes = "회원번호와 반려동물번호를 이용하여 정보 조회한다.")
    @GetMapping("/{userNumber}/{petNumber}")
    public ResponseEntity<petDto> petDetails(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "반려동물번호", required = true) @PathVariable("petNumber") int petNumber)
            throws Exception {

        logger.info("petDetails 호출");
        petDto petDto = petService.findPet(userNumber, petNumber);
        if(petDto != null) {
            return new ResponseEntity<petDto>(petDto, HttpStatus.OK); // 200: 성공
        }
        return new ResponseEntity<petDto>(petDto, HttpStatus.NO_CONTENT); // 요청 성공했지만 보내줄 내용 없음
    }

    // 반려동물 정보 추가
    @ApiOperation(value = "현재 회원의 반려동물 정보 추가", notes = "반려동물객체를 활용하여 생성합니다.")
    @PostMapping
    public ResponseEntity<String> petAdd(
            @ApiParam(value = "반려동물 객체", required = true) @RequestBody petDto petDto) throws Exception {

        logger.info("petAdd 호출");
        if(petService.addPet(petDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); // 200
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    // 특정 반려동물 정보 수정
    @ApiOperation(value = "현재 회원의 반려동물 정보 수정", notes = "반려동물 객체를 활용하여 정보를 수정합니다.")
    @PutMapping
    public ResponseEntity<String> petModify(
            @ApiParam(value = "반려동물 객체", required = true) @RequestBody petDto petDto) throws Exception {

        logger.info("petModify 호출");
        if(petService.modifyPet(petDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); // 200
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    // 특정 반려동물 삭제
    @ApiOperation(value = "현재 회원의 반려동물 삭제", notes = "회원번호와 반려동물번호를 이용하여 삭제한다.")
    @DeleteMapping("/{userNumber}/{petNumber}")
    public ResponseEntity<String> petRemove(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "반려동물번호", required = true) @PathVariable("petNumber") int petNumber)
            throws Exception {

        logger.info("petRemove 호출");
        if(petService.removePet(userNumber, petNumber)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); // 200
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
