package com.sns.pet.controller;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/main")
public class FeedController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private FeedService feedService;

    @PostMapping("/new-feed")
    public ResponseEntity<String> feedAdd(@RequestBody FeedDto feedDto) throws Exception {
        logger.info("feedAdd - 호출");
        if(feedService.feedAdd(feedDto)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{feedNumber}")
    public ResponseEntity<FeedDto> feedDetails(@PathVariable("feedNumber") int feedNumber) throws Exception{
        logger.info("feedDetails - 호출" + feedNumber);
        return new ResponseEntity<FeedDto>(feedService.feedDetails(feedNumber), HttpStatus.OK);
    }

    @PutMapping("/{feedNumber}")
    public ResponseEntity<String> feedModify(@RequestBody FeedDto feedDto) throws Exception{
        logger.info("feedModify - 호출");
        if(feedService.feedModify(feedDto)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{feedNumber}")
    public ResponseEntity<String> feedRemove(@RequestBody int feedNumber) throws Exception{
        logger.info("feedRemove");
        if(feedService.feedRemove(feedNumber)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
