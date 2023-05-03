package com.example.android.healthcare.Model;

public class OrderPlane  {

    private String  username;
    private  String fullname;
    private  String address;
    private  String contactno;
    private  String pincode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public OrderPlane() {
    }

    public OrderPlane(String username, String fullname, String address, String contactno, String pincode) {
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.contactno = contactno;
        this.pincode = pincode;
    }
}
