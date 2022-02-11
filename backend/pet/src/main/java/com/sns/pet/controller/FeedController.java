package com.sns.pet.controller;

import com.sns.pet.dto.FeedDto;
import com.sns.pet.dto.FeedPhotoDto;
import com.sns.pet.service.FeedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/main")
public class FeedController {

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final FeedService feedService;

    @ApiOperation(value = "뉴스 피드 목록 반환", response = List.class)
    @GetMapping("/newsfeed")
    public ResponseEntity<List<FeedDto>> newsFeedList(
            @ApiParam(value = "로그인 유저 번호", required = true) @RequestParam("userNumber") Long userNumber,
            @ApiParam(value = "default 빈문자열, 마지막 피드의 생성일", required = false) @RequestParam("cursor") String cursor) throws Exception {
        logger.info("뉴스 피드 목록 - 호출");
        return new ResponseEntity<>(feedService.findNewsFeedList(userNumber, cursor), HttpStatus.OK);
    }

    @ApiOperation(value = "닮은 동물 피드 목록 반환", response = List.class)
    @GetMapping("/favfeed")
    public ResponseEntity<List<FeedDto>> similarAnimalFeedList(
            @ApiParam(value = "로그인 유저 번호", required = true) @RequestParam("userNumber") Long userNumber,
            @ApiParam(value = "동물 번호", required = true) @RequestParam("animalNumber") int animalNumber,
            @ApiParam(value = "default 빈문자열, 마지막 피드의 생성일") @RequestParam("cursor") String cursor) throws Exception{
        logger.info("선호 동물 피드 목록 - 호출");
        return new ResponseEntity<>(feedService.findSimilarAnimalFeedList(userNumber, animalNumber, cursor), HttpStatus.OK);
    }

    @ApiOperation(value = "팔로우 피드 목록 반환", response = List.class)
    @GetMapping("/followfeed")
    public ResponseEntity<List<FeedDto>> followFeedList(
            @ApiParam(value = "로그인 유저 번호", required = true) @RequestParam("userNumber") Long userNumber,
            @ApiParam(value = "default 빈문자열, 마지막 피드의 생성일", required = false) @RequestParam("cursor") String cursor) throws Exception{
        logger.info("팔로우 피드 목록 - 호출");
        return new ResponseEntity<>(feedService.findFollowFeedList(userNumber, cursor), HttpStatus.OK);
    }

    @ApiOperation(value = "userNumber에 해당하는 내 피드 목록 반환", response = FeedDto.class)
    @GetMapping("/feed/list/{userNumber}")
    public ResponseEntity<List<FeedDto>> myFeedList(@PathVariable("userNumber") Long userNumber) throws Exception {
        logger.info("내 피드 목록 - 호출" + userNumber);
        List<FeedDto> feedDtoList = feedService.findMyFeedList(userNumber);

        // 이미지 변환
        InputStream imageStream;
        for (FeedDto feedDto : feedDtoList) {
            imageStream = new FileInputStream(feedDto.getFeedThumbnailFolder() + feedDto.getFeedThumbnailName());
            feedDto.setFeedThumbnail(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<>(feedDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "feed 생성, DB 입력 여부에 따라 success, fail 반환", response = FeedDto.class)
    @PostMapping("/feed")
    public ResponseEntity<String> feedAdd(@ApiParam(value = "피드 내용", required = true) @RequestPart(value = "feedContent") String feedContent,
                                          @ApiParam(value = "유저 번호", required = true) @RequestPart(value = "userNumber") String userNumber,
                                          @ApiParam(value = "피드 사진들", required = true) @RequestPart(value = "feedPhoto", required = false) List<MultipartFile> upImages) throws Exception {
        logger.info("feedAdd - 호출");
        logger.info("feedContent : {}", feedContent);
        logger.info("userNumber : {}", userNumber);
        FeedDto feedDto = new FeedDto();
        feedDto.setFeedContent(feedContent);
        feedDto.setUserNumber(Long.parseLong(userNumber));

        // fileUpload 설정
        if (upImages != null && !upImages.isEmpty()) {
            logger.info("file 확인");
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
//            String UPLOAD_PATH = File.separator + "Users" + File.separator + "leejuhyeong" + File.separator + "test";
            String UPLOAD_PATH = File.separator + "home" + File.separator + "test" + File.separator + "images";           // ec2 서버용
            String originName, fileExtension, saveFileName, saveFolder;

            logger.info("저장경로 확인 : {}", UPLOAD_PATH);
            // 폴더 생성
            saveFolder = UPLOAD_PATH + File.separator + today + File.separator;
            File folder = new File(saveFolder);
            if (!folder.exists()) {
                logger.info("폴더 생성 : {}", saveFolder);
                folder.mkdirs();
            }

            // 파일 저장
            List<FeedPhotoDto> feedPhotoDtoList = new ArrayList<>();
            for (MultipartFile image : upImages) {
                originName = image.getOriginalFilename();                                   // 파일이름.확장자

                if (originName != null) {
                    fileExtension = originName.substring(originName.lastIndexOf(".") + 1);  // 확장자
                    originName = originName.substring(0, originName.lastIndexOf("."));      // 파일이름
                    saveFileName = UUID.randomUUID() + originName + "." + fileExtension;
                    logger.info("파일 이름 생성 : {}", saveFileName);

                    logger.info("file 저장");
                    image.transferTo(new File(saveFolder, saveFileName));
                    logger.info("file list에 추가");
                    feedPhotoDtoList.add(new FeedPhotoDto(saveFolder, saveFileName));
                }
            }
            feedDto.setFeedPhotoDtoList(feedPhotoDtoList);
            feedDto.setFeedThumbnailFolder(feedPhotoDtoList.get(0).getSaveFolder());
            feedDto.setFeedThumbnailName(feedPhotoDtoList.get(0).getPhotoName());
        }

        logger.info("DB 저장 시작");
        if (feedService.addFeed(feedDto)) {
            logger.info("DB 저장 완료");
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        logger.info("DB 저장 실패");
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "feedNumber에 해당하는 피드 반환", response = FeedDto.class)
    @GetMapping("/feed/{userNumber}/{feedNumber}")
    public ResponseEntity<FeedDto> feedDetails(
            @ApiParam(value = "로그인 유저번호", required = true) @PathVariable("userNumber") Long userNumber,
            @ApiParam(value = "피드 번호", required = true) @PathVariable("feedNumber") Long feedNumber) throws Exception {
        logger.info("feedDetails - 호출" + feedNumber);
        FeedDto feedDto = feedService.findFeed(userNumber, feedNumber);

        // 이미지 변환
        InputStream imageStream;
        for (FeedPhotoDto feedPhotoDto : feedDto.getFeedPhotoDtoList()) {
            imageStream = new FileInputStream(feedPhotoDto.getSaveFolder() + feedPhotoDto.getPhotoName());
            feedPhotoDto.setPhoto(IOUtils.toByteArray(imageStream));
        }
        return new ResponseEntity<>(feedDto, HttpStatus.OK);
    }

    @ApiOperation(value = "image 경로에 따른 이미지 반환", response = FeedDto.class)
    @GetMapping("/image")
    public ResponseEntity<Resource> imageDetails(@RequestParam("file") String image) throws Exception {
        logger.info("imageDetails - 호출" + image);
        Resource resource = new FileSystemResource(image);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", Files.probeContentType(Paths.get(image)));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "feedNumber에 해당하는 피드 삭제, DB입력 성공 여부에 따라 success, fail 반환")
    @DeleteMapping("/feed/{feedNumber}")
    public ResponseEntity<String> feedRemove(@PathVariable("feedNumber") Long feedNumber) throws Exception {
        logger.info("feedRemove - 호출");
        if (feedService.removeFeed(feedNumber)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "feedNumber에 해당하는 이미지 반환", response = FeedDto.class)
    @PostMapping("/imagelist")
    public ResponseEntity<byte[][]> imageList(
            @ApiParam(value = "피드 번호", required = true) @RequestBody FeedDto feedNumbers) throws Exception {
        logger.info("imageList - 호출" + feedNumbers.toString());
        List<FeedPhotoDto> feedPhotoDtoList = feedService.listImage(feedNumbers);
        logger.info("feedPhotoDtoList - 호출" + feedPhotoDtoList.toString());
        // 이미지 변환
        byte[][] images = new byte[feedPhotoDtoList.size()][];
        int count = 0;
        InputStream imageStream;
        for (FeedPhotoDto feedPhotoDto : feedPhotoDtoList) {
            imageStream = new FileInputStream(feedPhotoDto.getSaveFolder() + feedPhotoDto.getPhotoName());
            images[count++] = IOUtils.toByteArray(imageStream);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

//    @ApiOperation(value = "feedNumber에 해당하는 피드 수정, 내용만 수정 가능. DB입력 성공 여부에 따라 success, fail 반환")
//    @PutMapping("/feed/{feedNumber}")
//    public ResponseEntity<String> feedModify(@RequestBody FeedDto feedDto) throws Exception{
//        logger.info("feedModify - 호출");
//        if(feedService.modifyFeed(feedDto)){
//            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
//    }
}
