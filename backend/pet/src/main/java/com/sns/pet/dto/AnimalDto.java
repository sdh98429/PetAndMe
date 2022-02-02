package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "선호동물 정보")
public class AnimalDto {
    @ApiModelProperty(value = "선호동물 번호")
    private Long animalNumber;
    @ApiModelProperty(value = "선호동물 이름")
    private String animalName;
}
