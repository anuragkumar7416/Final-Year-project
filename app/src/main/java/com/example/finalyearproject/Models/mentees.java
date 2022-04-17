package com.example.finalyearproject.Models;

public class mentees {

    String name, phoneNo,email,password, profilePic,workBackground,userId,dob,gender;


    public mentees() {

    }

    public mentees(String name, String phoneNo, String email, String password,String dob,String gender) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getWorkBackground() {
        return workBackground;
    }

    public void setWorkBackground(String workBackground) {
        this.workBackground = workBackground;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
