package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "선호동물")
public class FavAnimalDto {
    @ApiModelProperty(value = "회원 번호")
    private Long userNumber;
    @ApiModelProperty(value = "선호동물 번호")
    private int animalNumber;
}
