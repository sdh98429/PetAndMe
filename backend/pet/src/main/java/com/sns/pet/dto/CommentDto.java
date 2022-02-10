package com.sns.pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {
    private Long feedNumber;
    private Long commentNumber;
    private String commentContent;
    private String commentDate;
    private Long userNumber;
    private String userID;
    private String userNickName;
    private String userPhotoName;
    private String saveFolder;
    private byte[] userProfilePhoto;
}
