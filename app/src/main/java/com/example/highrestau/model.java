package com.example.highrestau;

public class model {

    String fullname,email,phone,dept,address;


    model(){


    }

    public model(String fullname, String email, String phone, String address,String dept) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
