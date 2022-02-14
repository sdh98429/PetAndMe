package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "user pet join dto")
public class UserPetDto {
    @ApiModelProperty(value = "회원 아이디")
    private String userID;
    @ApiModelProperty(value = "회원 닉네임")
    private String userNickName;
    @ApiModelProperty(value = "사진 저장 폴더")
    private String saveFolder;
    @ApiModelProperty(value = "회원 프로필 사진")
    private String userPhotoName;
    @ApiModelProperty(value = "회원 프로필 소개")
    private String userProfileContent;
    @ApiModelProperty(value = "반려동물 이름")
    private String petName;
    @ApiModelProperty(value = "반려동물 성별")
    private String petGender;
    @ApiModelProperty(value = "반려동물 생일")
    private String petBirth;
    @ApiModelProperty(value = "동물 이름")
    private String animalName;
}
