package com.sns.pet.controller;

import com.sns.pet.dto.CommentDto;
import com.sns.pet.service.CommentService;
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
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final CommentService commentService;

    @ApiOperation(value = "feedNumber 피드에 userNumber 사용자가 댓글 생성, DB입력 성공 여부에 따라 success, fail 반환")
    @PostMapping("/{feedNumber}/{userNumber}")
    public ResponseEntity<String> commentAdd(@PathVariable("feedNumber") Long feedNumber, @PathVariable("userNumber") Long userNumber, @RequestBody CommentDto commentDto) throws Exception{
        logger.info("commentAdd - 호출");
        commentDto.setUserNumber(userNumber);
        commentDto.setFeedNumber(feedNumber);
        if(commentService.addComment(commentDto)){
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "feedNumber 피드의 댓글 리스트 가져오기, DB입력 성공 여부에 따라 success, fail 반환")
    @GetMapping("/{feedNumber}")
    public ResponseEntity<List<CommentDto>> commentList(@PathVariable("feedNumber") Long feedNumber) throws Exception{
        logger.info("commentList - 호출");
        InputStream image;
        List<CommentDto> commentDtoList = commentService.findCommentList(feedNumber);
        for (CommentDto commentDto : commentDtoList) {
            image = new FileInputStream(commentDto.getSaveFolder() + commentDto.getUserPhotoName());
            commentDto.setUserProfilePhoto(IOUtils.toByteArray(image));
        }
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "commentNumber에 해당하는 댓글 삭제, DB입력 성공 여부에 따라 success, fail 반환")
    @DeleteMapping("/{commentNumber}")
    public ResponseEntity<String> commentRemove(@PathVariable("commentNumber") Long feedNumber, @PathVariable("commentNumber") Long commentNumber) throws Exception{
        logger.info("commentDelet - 호출");
        if(commentService.deleteComment(commentNumber)){
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
