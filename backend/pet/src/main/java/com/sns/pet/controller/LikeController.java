package com.sns.pet.controller;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.service.LikeService;
import io.swagger.annotations.ApiOperation;
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
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/like")
public class LikeController {

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final LikeService likeService;

    @ApiOperation(value = "userNumber 사용자가 feedNumber에 좋아요 누름", response = FeedDto.class)
    @PostMapping("/{userNumber}/{feedNumber}")
    public ResponseEntity<String> likeAdd(@PathVariable("userNumber") Long userNumber, @PathVariable("feedNumber") Long feedNumber) throws Exception {
        if(likeService.addLike(userNumber, feedNumber)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "userNumber 사용자가 feedNumber에 좋아요 취소", response = FeedDto.class)
    @DeleteMapping("/{userNumber}/{feedNumber}")
    public ResponseEntity<String> likeRemove(@PathVariable("userNumber") Long userNumber, @PathVariable("feedNumber") Long feedNumber) throws Exception {
        if(likeService.removeLike(userNumber, feedNumber)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "feedNumber 피드를 좋아요 누른 사람들 리스트", response = FeedDto.class)
    @GetMapping("/{feedNumber}")
    public ResponseEntity<List<UserDto>> likeList(@PathVariable("feedNumber") Long feedNumber) throws Exception {
        InputStream imageStream;
        List<UserDto> likeList = likeService.findLikeList(feedNumber);
        for (UserDto userDto : likeList) {
            imageStream = new FileInputStream(userDto.getSaveFolder() + userDto.getUserPhotoName());
            userDto.setUserProfilePhoto(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<>(likeList, HttpStatus.OK);
    }

}
