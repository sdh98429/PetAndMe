package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("이메일 인증")
public class EmailDto {
    @ApiModelProperty(value = "이메일")
    private String userEmail;
    @ApiModelProperty(value = "인증키")
    private String authKey;
}
