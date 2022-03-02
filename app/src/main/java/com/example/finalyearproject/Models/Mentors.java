package com.example.finalyearproject.Models;

public class Mentors {

    String name, phoneNo,email,password, profilePic,workBackground;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    int image_id;

    public Mentors(String name, int image_id, String workBackground) {
        this.name = name;
        this.image_id = image_id;
        this.workBackground = workBackground;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
