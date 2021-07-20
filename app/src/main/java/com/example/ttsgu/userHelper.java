package com.example.ttsgu;

public class userHelper {
    String name,uname,email,password,phoneNo,branch;
    int spNo,sp2no;

    public userHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSpNo() {
        return spNo;
    }

    public void setSpNo(int spNo) {
        this.spNo = spNo;
    }

    public int getSp2no() {
        return sp2no;
    }

    public void setSp2no(int sp2no) {
        this.sp2no = sp2no;
    }

    public userHelper(String name, String uname, String email, String password, String phoneNo, String branch, int spNo, int sp2no) {
        this.name = name;
        this.uname = uname;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.branch = branch;
        this.spNo = spNo;
        this.sp2no = sp2no;

    }
}
