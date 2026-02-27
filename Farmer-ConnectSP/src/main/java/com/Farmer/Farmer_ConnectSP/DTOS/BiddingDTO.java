/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.DTOS;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
public class BiddingDTO {

    private Integer bid;
    private BigDecimal biddingPrice;
    private Date datetime;
    private Integer status;
    private Integer customerId;
    private Integer productId;
    private String customername;
    private String img1;
    private String img2;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public BigDecimal getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(BigDecimal biddingPrice) {
        this.biddingPrice = biddingPrice;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }



}
