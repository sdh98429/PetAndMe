package com.sns.pet.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FeedPhotoDto {
    @ApiModelProperty(value = "피드 번호")
    private Long feedNumber;
    @ApiModelProperty(value = "이미지 저장 경로")
    private String saveFolder;
    @ApiModelProperty(value = "이미지 이름")
    private String photoName;
    @ApiModelProperty(value = "이미지")
    private byte[] photo;

    public FeedPhotoDto() {}

    public FeedPhotoDto(String saveFolder, String photoName) {
        this.saveFolder = saveFolder;
        this.photoName = photoName;
    }

    @Override
    public String toString() {
        return "FileInfoDto{" +
                "saveFolder='" + saveFolder + '\'' +
                ", photoName='" + photoName + '\'' +
                '}';
    }
}
