package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "회원")
public class UserDto {
    @ApiModelProperty(value = "회원 번호")
    private Long userNumber;
    @ApiModelProperty(value = "회원 아이디")
    private String userID;
    @ApiModelProperty(value = "회원 비밀번호")
    private String userPW;
    @ApiModelProperty(value = "회원 닉네임")
    private String userNickName;
    @ApiModelProperty(value = "회원 이메일")
    private String userEmail;
    @ApiModelProperty(value = "회원 반려동물 유/무")
    private boolean petCheck;
    @ApiModelProperty(value = "사진 저장 폴더")
    private String saveFolder;
    @ApiModelProperty(value = "회원 프로필 사진이름")
    private String userPhotoName;
    @ApiModelProperty(value = "회원 프로필 사진")
    private byte[] userProfilePhoto;
    @ApiModelProperty(value = "회원 프로필 소개")
    private String userProfileContent;
    @ApiModelProperty(value = "회원 탈퇴여부")
    private boolean userType;

}
