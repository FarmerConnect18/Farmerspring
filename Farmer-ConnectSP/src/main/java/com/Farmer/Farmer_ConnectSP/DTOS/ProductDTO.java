/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.DTOS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
public class ProductDTO {

    private Integer pid;
    private String productName;
    private String productType;
    private String quantity;
    private String quality;
    private BigDecimal price;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date datetime;
    private Integer status;

    private Integer farmerId;
    private String farmerName;

    private MultipartFile img1;
    private MultipartFile img2;
    private String imgname1;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public MultipartFile getImg1() {
        return img1;
    }

    public void setImg1(MultipartFile img1) {
        this.img1 = img1;
    }

    public MultipartFile getImg2() {
        return img2;
    }

    public void setImg2(MultipartFile img2) {
        this.img2 = img2;
    }

    public String getImgname1() {
        return imgname1;
    }

    public void setImgname1(String imgname1) {
        this.imgname1 = imgname1;
    }

    
}
