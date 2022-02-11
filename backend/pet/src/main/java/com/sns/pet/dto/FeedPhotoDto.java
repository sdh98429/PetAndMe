package com.sns.pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FeedPhotoDto {
    private String feedNumber;
    private String saveFolder;
    private String photoName;
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
