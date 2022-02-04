package com.sns.pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userNumber;
    private String userID;
    private String userPW;
    private String userNickName;
    private String userName;
    private String userEmail;
    private boolean petCheck;
    private String userProfilePicture;
    private String userProfileContent;
    private boolean userType;

}
