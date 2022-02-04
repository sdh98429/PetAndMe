package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "동물정보")
public class AnimalDto {
    @ApiModelProperty(value = "동물 번호")
    private int animalNumber;
    @ApiModelProperty(value = "동물 정보")
    private String animalName;
}
