package com.sns.pet.dto;

public class FeedDto {
    private int feedNumber;
    private String userID;
    private String feedContent;
    private String feedDate;
    private int cntLike;
    private int cntComment;

    public int getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(int feedNumber) {
        this.feedNumber = feedNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public String getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    public int getCntLike() {
        return cntLike;
    }

    public void setCntLike(int cntLike) {
        this.cntLike = cntLike;
    }

    public int getCntComment() {
        return cntComment;
    }

    public void setCntComment(int cntComment) {
        this.cntComment = cntComment;
    }

}
