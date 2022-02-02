package com.sns.pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileInfoDto {
    private String saveFolder;
    private String photoName;

    public FileInfoDto() {}

    public FileInfoDto(String saveFolder, String photoName) {
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
