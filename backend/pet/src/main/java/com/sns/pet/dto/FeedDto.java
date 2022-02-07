package com.sns.pet.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FeedDto {
    @ApiModelProperty(value = "피드 번호")
    private Long feedNumber;
    @ApiModelProperty(value = "피드 직성자 번호")
    private Long userNumber;
    @ApiModelProperty(value = "피드 직성자 아이디")
    private String userID;
    @ApiModelProperty(value = "피드 직성자 닉네임")
    private String userNickName;
    @ApiModelProperty(value = "피드 내용")
    private String feedContent;
    @ApiModelProperty(value = "피드 작성일")
    private String feedDate;
    @ApiModelProperty(value = "피드 썸네일 폴더")
    private String feedThumbnailFolder;
    @ApiModelProperty(value = "피드 썸네일 이름")
    private String feedThumbnailName;
    @ApiModelProperty(value = "피드 썸네일 파일")
    private byte[] feedThumbnail;
    @ApiModelProperty(value = "피드 사진 리스트")
    private List<FeedPhotoDto> feedPhotoDtoList;
    @ApiModelProperty(value = "좋아요 개수")
    private int cntLike;
    @ApiModelProperty(value = "댓글 개수")
    private int cntComment;
    @ApiModelProperty(value = "로그인 유저가 해당 피드 좋아요를 눌렀으면 true, 아니면 false")
    private boolean checkLike;

    public FeedDto() {}

    @Override
    public String toString() {
        return "FeedDto{" +
                "feedNumber=" + feedNumber +
                ", userNumber=" + userNumber +
                ", feedContent='" + feedContent + '\'' +
                ", feedDate='" + feedDate + '\'' +
                ", feedThumbnailFolder='" + feedThumbnailFolder + '\'' +
                ", feedThumbnailName='" + feedThumbnailName + '\'' +
                ", fileInfoDtoList=" + feedPhotoDtoList +
                '}';
    }
}
