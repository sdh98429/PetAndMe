package com.sns.pet.dto;

public class feedPhotoDto {
    private int feedPhotoNumber;
    private String feedPhotoName;
    private int feedNumber;

    public int getFeedPhotoNumber() {
        return feedPhotoNumber;
    }

    public void setFeedPhotoNumber(int feedPhotoNumber) {
        this.feedPhotoNumber = feedPhotoNumber;
    }

    public String getFeedPhotoName() {
        return feedPhotoName;
    }

    public void setFeedPhotoName(String feedPhotoName) {
        this.feedPhotoName = feedPhotoName;
    }

    public int getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(int feedNumber) {
        this.feedNumber = feedNumber;
    }
}
