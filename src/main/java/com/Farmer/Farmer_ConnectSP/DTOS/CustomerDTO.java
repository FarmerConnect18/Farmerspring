/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.DTOS;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
public class CustomerDTO {

    private Integer cid;
    private String username;
    private String email;
    private String password;
    private String phoneno;
    private String address;
    private String state;
    private String city;
    private MultipartFile customerImg; 
    private MultipartFile customerAdhar;
    private MultipartFile customerAdharback;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date datetime;
    private Integer status;

    // If you want, you can include IDs of related entities
    private List<Integer> wasteRequestIds;
    private List<Integer> biddingIds;

    public CustomerDTO(Integer cid, String username, String email, String password, String phoneno, String address, String state, String city, MultipartFile customerImg, MultipartFile customerAdhar, MultipartFile customerAdharback, Date datetime, Integer status, List<Integer> wasteRequestIds, List<Integer> biddingIds) {
        this.cid = cid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.address = address;
        this.state = state;
        this.city = city;
        this.customerImg = customerImg;
        this.customerAdhar = customerAdhar;
        this.customerAdharback = customerAdharback;
        this.datetime = datetime;
        this.status = status;
        this.wasteRequestIds = wasteRequestIds;
        this.biddingIds = biddingIds;
    }

    public CustomerDTO() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public MultipartFile getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(MultipartFile customerImg) {
        this.customerImg = customerImg;
    }

    public MultipartFile getCustomerAdhar() {
        return customerAdhar;
    }

    public void setCustomerAdhar(MultipartFile customerAdhar) {
        this.customerAdhar = customerAdhar;
    }

    public MultipartFile getCustomerAdharback() {
        return customerAdharback;
    }

    public void setCustomerAdharback(MultipartFile customerAdharback) {
        this.customerAdharback = customerAdharback;
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

    public List<Integer> getWasteRequestIds() {
        return wasteRequestIds;
    }

    public void setWasteRequestIds(List<Integer> wasteRequestIds) {
        this.wasteRequestIds = wasteRequestIds;
    }

    public List<Integer> getBiddingIds() {
        return biddingIds;
    }

    public void setBiddingIds(List<Integer> biddingIds) {
        this.biddingIds = biddingIds;
    }

    public void getRole() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
