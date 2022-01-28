package com.sns.pet.dto;

public class userDto {
    private int userNumber;
    private String userID;
    private String userPW;
    private String userNickName;
    private String userName;
    private String userEmail;
    private boolean petCheck;
    private String userProfilePicture;
    private String userProfileContent;
    private boolean userType;

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isPetCheck() {
        return petCheck;
    }

    public void setPetCheck(boolean petCheck) {
        this.petCheck = petCheck;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getUserProfileContent() {
        return userProfileContent;
    }

    public void setUserProfileContent(String userProfileContent) {
        this.userProfileContent = userProfileContent;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }
}
