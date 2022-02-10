package com.sns.pet.controller;

import com.sns.pet.dto.PetDto;
import com.sns.pet.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/user/pet")
public class PetController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PetController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final PetService petService;

    // 모든 반려동물 목록 조회
    @ApiOperation(value = "모든 반려동물 정보 조회", notes = "회원번호를 이용하여 회원의 모든 반려동물 정보를 조회합니다.")
    @GetMapping("/{userNumber}")
    public ResponseEntity<List<PetDto>> petList(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber) throws Exception {

        logger.info("petList 호출");
        return new ResponseEntity<List<PetDto>>(petService.findAllPets(userNumber), HttpStatus.OK);
    }

    // 특정 반려동물 상세정보 조회
    @ApiOperation(value = "반려동물 상세정보 조회", notes = "회원번호와 반려동물번호를 이용하여 정보 조회한다.")
    @GetMapping("/{userNumber}/{petNumber}")
    public ResponseEntity<PetDto> petDetails(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "반려동물번호", required = true) @PathVariable("petNumber") Long petNumber)
            throws Exception {

        logger.info("petDetails 호출");
        return new ResponseEntity<PetDto>(petService.findPet(userNumber, petNumber), HttpStatus.OK); // 200: 성공
    }

    // 유저의 반려동물 이름 중복 체크
    @ApiOperation(value = "현재 회원의 반려동물 이름 중복 체크", notes = "유저의 petName이 중복되는지 체크. 중복이 아니면 success 반환")
    @GetMapping("/ckpet/{userNumber}/{petName}")
    public ResponseEntity<String> petNameCheck(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "반려동물 이름", required = true) @PathVariable("petName") String petName)
            throws Exception {

        logger.info("petNameCheck 호출");
        if(petService.checkPetName(userNumber, petName)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        // 중복되는 경우
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    // 반려동물 정보 추가
    @ApiOperation(value = "현재 회원의 반려동물 정보 추가", notes = "반려동물객체를 활용하여 생성합니다.")
    @PostMapping
    public ResponseEntity<String> petAdd(
            @ApiParam(value = "반려동물 객체", required = true) @RequestBody PetDto petDto) throws Exception {

        logger.info("petAdd 호출");
        if(petService.addPet(petDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); // 200
        }
        // userNumber가 이미 petName를 가지고 있어서 추가 안함
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT); // 204: 요청 성공했지만 보내줄 내용 없음
    }

    // 특정 반려동물 삭제
    @ApiOperation(value = "현재 회원의 반려동물 삭제", notes = "회원번호와 반려동물번호를 이용하여 삭제한다.")
    @DeleteMapping("/{userNumber}/{petNumber}")
    public ResponseEntity<String> petRemove(
            @ApiParam(value = "반려인 회원번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "반려동물번호", required = true) @PathVariable("petNumber") Long petNumber)
            throws Exception {

        logger.info("petRemove 호출");
        if(petService.removePet(userNumber, petNumber)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); // 200
        }
        // userNumber가 petNumber를 가지고 있지 않은 경우
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
