package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "반려동물 정보")
public class PetDto {
    @ApiModelProperty(value = "반려인 회원번호")
    private Long userNumber;
    @ApiModelProperty(value = "반려동물 번호")
    private Long petNumber;
    @ApiModelProperty(value = "반려동물 종류")
    private int animalNumber;
    @ApiModelProperty(value = "반려동물 이름", example = "초코")
    private String petName;
    @ApiModelProperty(value = "반려동물 성별", example = "M")
    private String petGender;
    @ApiModelProperty(value = "반려동물 생일", example = "2022-02-01")
    private String petBirth;
}
