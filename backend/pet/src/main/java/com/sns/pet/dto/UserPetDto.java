package com.sns.pet.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "user pet join dto")
public class UserPetDto {
    private String userProfilePicture;
    private String userNickName;
    private String userID;
    private String petName;
    private int animalNumber;
    private String petBirth;
}
