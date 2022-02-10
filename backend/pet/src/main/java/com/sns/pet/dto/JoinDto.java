package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("회원가입 정보")
public class JoinDto {
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
    @ApiModelProperty(value = "회원 프로필 소개")
    private String userProfileContent;
    @ApiModelProperty(value = "회원 반려동물 유/무")
    private boolean petCheck;
    @ApiModelProperty(value = "반려동물 정보 리스트")
    private List<PetDto> userPet;
    @ApiModelProperty(value = "선호동물 정보 리스트")
    private List<FavAnimalDto> userPreference;

    @Override
    public String toString() {
        return "JoinDto{" +
                "userNumber=" + userNumber +
                ", userID='" + userID + '\'' +
                ", userPW='" + userPW + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", petCheck=" + petCheck +
                ", userPet=" + userPet +
                ", userPreference=" + userPreference +
                '}';
    }
}
