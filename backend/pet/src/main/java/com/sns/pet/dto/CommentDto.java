package com.sns.pet.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {
    @ApiModelProperty(value = "피드 번호")
    private Long feedNumber;
    @ApiModelProperty(value = "댓글 번호")
    private Long commentNumber;
    @ApiModelProperty(value = "댓글 내용")
    private String commentContent;
    @ApiModelProperty(value = "댓글 생성일")
    private String commentDate;
    @ApiModelProperty(value = "댓글 등록자 번호(유저 번호)")
    private Long userNumber;
    @ApiModelProperty(value = "댓글 등록자 ID(유저 ID)")
    private String userID;
    @ApiModelProperty(value = "댓글 등록자 닉네임(유저 닉네임)")
    private String userNickName;
    @ApiModelProperty(value = "댓글 등록자 프로필 이름(유저 프로필 이름)")
    private String userPhotoName;
    @ApiModelProperty(value = "댓글 등록자 프로필 저장 폴더(유저 프로필 저장 폴더)")
    private String saveFolder;
    @ApiModelProperty(value = "댓글 등록자 프로필 사진")
    private byte[] userProfilePhoto;
}
