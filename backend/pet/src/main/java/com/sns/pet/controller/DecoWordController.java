package com.sns.pet.controller;

import com.sns.pet.service.DecoWordService;
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
@RequestMapping("/deco")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class DecoWordController {

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final DecoWordService decoWordService;

    @ApiOperation(value = "닉네임 추천", notes = "임의의 닉네임을 만들어준다.")
    @GetMapping("/{animalNumber}")
    public ResponseEntity<List<String>> nickNameMake (
            @ApiParam(value = "동물 번호") @PathVariable("animalNumber") int animalNumber) throws Exception{
        logger.info("nickNameMake 호출");
        return new ResponseEntity<List<String>>(decoWordService.makeNickName(animalNumber), HttpStatus.OK);
    }

}
