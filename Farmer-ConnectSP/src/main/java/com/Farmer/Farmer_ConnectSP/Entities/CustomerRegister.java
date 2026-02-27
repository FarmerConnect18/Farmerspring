/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author preml
 */
@Entity
@Table(name = "customer_register")
@NamedQueries({
    @NamedQuery(name = "CustomerRegister.findAll", query = "SELECT c FROM CustomerRegister c"),
    @NamedQuery(name = "CustomerRegister.findByCid", query = "SELECT c FROM CustomerRegister c WHERE c.cid = :cid"),
    @NamedQuery(name = "CustomerRegister.findByUsername", query = "SELECT c FROM CustomerRegister c WHERE c.username = :username"),
    @NamedQuery(name = "CustomerRegister.findByEmail", query = "SELECT c FROM CustomerRegister c WHERE c.email = :email"),
    @NamedQuery(name = "CustomerRegister.findByPassword", query = "SELECT c FROM CustomerRegister c WHERE c.password = :password"),
    @NamedQuery(name = "CustomerRegister.findByPhoneno", query = "SELECT c FROM CustomerRegister c WHERE c.phoneno = :phoneno"),
    @NamedQuery(name = "CustomerRegister.findByState", query = "SELECT c FROM CustomerRegister c WHERE c.state = :state"),
    @NamedQuery(name = "CustomerRegister.findByCity", query = "SELECT c FROM CustomerRegister c WHERE c.city = :city"),
    @NamedQuery(name = "CustomerRegister.findByCustomerImg", query = "SELECT c FROM CustomerRegister c WHERE c.customerImg = :customerImg"),
    @NamedQuery(name = "CustomerRegister.findByField", query = "SELECT c FROM CustomerRegister c WHERE c.Role = :Role"),
    @NamedQuery(name = "CustomerRegister.findByCustomerAdhar", query = "SELECT c FROM CustomerRegister c WHERE c.customerAdhar = :customerAdhar"),
    @NamedQuery(name = "CustomerRegister.findByDatetime", query = "SELECT c FROM CustomerRegister c WHERE c.datetime = :datetime"),
    @NamedQuery(name = "CustomerRegister.findByStatus", query = "SELECT c FROM CustomerRegister c WHERE c.status = :status")})
public class CustomerRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneno")
    private String phoneno;
    @Lob
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "customer_img")
    private String customerImg;
    @Column(name = "Role")
    private String Role;
    @Column(name = "customer_adhar")
    private String customerAdhar;
    @Column(name = "customerAdharback")
    private String customerAdharback;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "status")
    private Integer status;
    @JsonIgnore
    @OneToMany(mappedBy = "customerId")
    private List<Bidding> biddingList;
    @OneToMany(mappedBy = "customerId")
    private List<JunctionRequest> junctionRequestList;

    public String getCustomerAdharback() {
        return customerAdharback;
    }

    public void setCustomerAdharback(String customerAdharback) {
        this.customerAdharback = customerAdharback;
    }

    public CustomerRegister() {
    }

    public CustomerRegister(Integer cid) {
        this.cid = cid;
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

//    public String getCustomerImg() {
//        return customerImg;
//    }
//
//    public void setCustomerImg(String customerImg) {
//        this.customerImg = customerImg;
//    }
    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getCustomerAdhar() {
        return customerAdhar;
    }

    public void setCustomerAdhar(String customerAdhar) {
        this.customerAdhar = customerAdhar;
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

    public List<Bidding> getBiddingList() {
        return biddingList;
    }

    public void setBiddingList(List<Bidding> biddingList) {
        this.biddingList = biddingList;
    }

    public List<JunctionRequest> getJunctionRequestList() {
        return junctionRequestList;
    }

    public void setJunctionRequestList(List<JunctionRequest> junctionRequestList) {
        this.junctionRequestList = junctionRequestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerRegister)) {
            return false;
        }
        CustomerRegister other = (CustomerRegister) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister[ cid=" + cid + " ]";
    }

}
