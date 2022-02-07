package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "검색관련 정보")
public class SearchDto {
    @ApiModelProperty(value = "회원번호")
    private Long userNumber;
    @ApiModelProperty(value = "검색어")
    private String searchWord;
    @ApiModelProperty(value = "검색날짜")
    private String searchDate;
}
