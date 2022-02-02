package com.sns.pet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FeedDto {
    private Long feedNumber;
    private Long userNumber;
    private String feedContent;
    private String feedDate;
    private String feedThumbnailFolder;
    private String feedThumbnailName;
    private int cntLike;
    private int cntComment;
    private byte[] feedThumbnail;
    private List<FileInfoDto> fileInfoDtoList;

    public FeedDto() {}

    @Override
    public String toString() {
        return "FeedDto{" +
                "feedNumber=" + feedNumber +
                ", userNumber=" + userNumber +
                ", feedContent='" + feedContent + '\'' +
                ", feedDate='" + feedDate + '\'' +
                ", feedThumbnailFolder='" + feedThumbnailFolder + '\'' +
                ", feedThumbnailName='" + feedThumbnailName + '\'' +
                ", fileInfoDtoList=" + fileInfoDtoList +
                '}';
    }
}
