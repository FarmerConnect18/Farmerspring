/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author preml
 */
@Entity
@Table(name = "bidding")
@NamedQueries({
    @NamedQuery(name = "Bidding.findAll", query = "SELECT b FROM Bidding b"),
    @NamedQuery(name = "Bidding.findByBid", query = "SELECT b FROM Bidding b WHERE b.bid = :bid"),
    @NamedQuery(name = "Bidding.findByBiddingPrice", query = "SELECT b FROM Bidding b WHERE b.biddingPrice = :biddingPrice"),
    @NamedQuery(name = "Bidding.findByDatetime", query = "SELECT b FROM Bidding b WHERE b.datetime = :datetime"),
    @NamedQuery(name = "Bidding.findByStatus", query = "SELECT b FROM Bidding b WHERE b.status = :status")})
public class Bidding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bid")
    private Integer bid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bidding_price")
    private BigDecimal biddingPrice;
    @Column(name = "datetime")
    @CreationTimestamp
    private Date datetime;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "customer_id", referencedColumnName = "cid")
    @ManyToOne
    private CustomerRegister customerId;
    @JoinColumn(name = "product_id", referencedColumnName = "pid")
    @JsonIgnore
    @ManyToOne
    private Product productId;

    public Bidding() {
    }

    public Bidding(Integer bid) {
        this.bid = bid;
    }

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

    public CustomerRegister getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerRegister customerId) {
        this.customerId = customerId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bid != null ? bid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bidding)) {
            return false;
        }
        Bidding other = (Bidding) object;
        if ((this.bid == null && other.bid != null) || (this.bid != null && !this.bid.equals(other.bid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.Bidding[ bid=" + bid + " ]";
    }
    
}
