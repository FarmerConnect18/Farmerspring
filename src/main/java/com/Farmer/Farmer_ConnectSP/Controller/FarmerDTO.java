/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
public class FarmerDTO {

//    public FarmerDTO(String address1, String city1, String email1, String farmerAdhar1, String password1, String state1, Integer status1, Integer fid1, String username1, String profileimg,String role) {
//    }

    private Integer fid;
    private String username;
    private String email;
    private String password;
    private String phoneno;
    private String address;
    private String state;
    private String city;
    private String role;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date datetime;
    private Integer status;
    private MultipartFile farmerImg;
    private MultipartFile farmerAdhar;
    private MultipartFile farmerAdharback;

    public MultipartFile getFarmerAdharback() {
        return farmerAdharback;
    }

    public void setFarmerAdharback(MultipartFile farmerAdharback) {
        this.farmerAdharback = farmerAdharback;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MultipartFile getFarmerImg() {
        return farmerImg;
    }

    public void setFarmerImg(MultipartFile farmerImg) {
        this.farmerImg = farmerImg;
    }

    public MultipartFile getFarmerAdhar() {
        return farmerAdhar;
    }

    public void setFarmerAdhar(MultipartFile farmerAdhar) {
        this.farmerAdhar = farmerAdhar;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    void setDatetime(String datetime) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
