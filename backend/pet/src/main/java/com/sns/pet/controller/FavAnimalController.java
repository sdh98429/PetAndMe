package com.sns.pet.controller;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.FavAnimalDto;
import com.sns.pet.service.FavAnimalService;
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
@RequestMapping("/animal")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class FavAnimalController {

    private static final Logger logger = LoggerFactory.getLogger(FavAnimalController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final FavAnimalService favAnimalService;

    // favAnimalAdd, favAnimalModify, favAnimalRemove, favAnimalList, animalList
    @ApiOperation(value = "동물 목록 조회", notes = "동물 목록을 조회한다.")
    @GetMapping
    public ResponseEntity<List<AnimalDto>> animalList() throws Exception {
        logger.info("animalList 조회");
        List<AnimalDto> animalDto = favAnimalService.findAnimal();
        return new ResponseEntity<List<AnimalDto>>(animalDto, HttpStatus.OK);
    }

    @ApiOperation(value = "선호동물 목록 조회", notes = "유저의 선호동물 목록을 조회한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @GetMapping("/{userNumber}")
    public ResponseEntity<List<FavAnimalDto>> favAnimalList(@PathVariable("userNumber") @ApiParam(value = "조회할 회원번호") Long userNumber) throws Exception {
        logger.info("favAnimalList 조회");
        List<FavAnimalDto> favAnimalDtoList = favAnimalService.findFavAnimal(userNumber);
        return new ResponseEntity<List<FavAnimalDto>>(favAnimalDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "선호동물 등록", notes = "선호동물을 등록한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @PostMapping
    public ResponseEntity<String> favAnimalAdd(@RequestBody @ApiParam(value = "등록할 선호동물", required = true) List<FavAnimalDto> favAnimalDtoList) throws Exception {
        logger.info("favAnimalAdd 호출");
        if (favAnimalService.addFavAnimal(favAnimalDtoList)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "선호동물 수정", notes = "선호동물을 수정한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @PutMapping
    public ResponseEntity<String> favAnimalModify(@RequestBody @ApiParam(value = "수정할 선호동물")List<FavAnimalDto> favAnimalDtoList) throws Exception{
        logger.info("favAnaimalModify 호출");
        if(favAnimalService.modifyFavAnimal(favAnimalDtoList)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "선호동물 삭제", notes = "선호동물을 삭제한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @DeleteMapping("/{userNumber}")
    public ResponseEntity<String> favAnimalRemove(@PathVariable("userNumber") @ApiParam(value = "회원번호", required = true) Long userNumber) throws Exception {
        logger.info("favAnimalRemove 호출");
        if (favAnimalService.removeFavAnimal(userNumber)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
    }


}
