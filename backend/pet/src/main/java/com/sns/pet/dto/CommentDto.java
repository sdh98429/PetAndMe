package com.sns.pet.dto;

public class CommentDto {
    private int feedNumber;
    private int commentNumber;
    private int userNumber;
    private String commentContent;
    private String commentDate;

    public int getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(int feedNumber) {
        this.feedNumber = feedNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
