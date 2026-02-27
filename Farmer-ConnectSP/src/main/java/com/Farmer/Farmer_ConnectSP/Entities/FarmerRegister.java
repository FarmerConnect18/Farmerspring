/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "farmer_register")
@NamedQueries({
    @NamedQuery(name = "FarmerRegister.findAll", query = "SELECT f FROM FarmerRegister f"),
    @NamedQuery(name = "FarmerRegister.findByFid", query = "SELECT f FROM FarmerRegister f WHERE f.fid = :fid"),
    @NamedQuery(name = "FarmerRegister.findByUsername", query = "SELECT f FROM FarmerRegister f WHERE f.username = :username"),
    @NamedQuery(name = "FarmerRegister.findByEmail", query = "SELECT f FROM FarmerRegister f WHERE f.email = :email"),
    @NamedQuery(name = "FarmerRegister.findByPassword", query = "SELECT f FROM FarmerRegister f WHERE f.password = :password"),
    @NamedQuery(name = "FarmerRegister.findByPhoneno", query = "SELECT f FROM FarmerRegister f WHERE f.phoneno = :phoneno"),
    @NamedQuery(name = "FarmerRegister.findByState", query = "SELECT f FROM FarmerRegister f WHERE f.state = :state"),
    @NamedQuery(name = "FarmerRegister.findByCity", query = "SELECT f FROM FarmerRegister f WHERE f.city = :city"),
    @NamedQuery(name = "FarmerRegister.findByFarmerImg", query = "SELECT f FROM FarmerRegister f WHERE f.farmerImg = :farmerImg"),
    @NamedQuery(name = "FarmerRegister.findByFarmerAdhar", query = "SELECT f FROM FarmerRegister f WHERE f.farmerAdhar = :farmerAdhar"),
    @NamedQuery(name = "FarmerRegister.findByDatetime", query = "SELECT f FROM FarmerRegister f WHERE f.datetime = :datetime"),
    @NamedQuery(name = "FarmerRegister.findByStatus", query = "SELECT f FROM FarmerRegister f WHERE f.status = :status")})
public class FarmerRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid")
    private Integer fid;
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
    @Column(name = "farmer_img")
    private String farmerImg;
    @Column(name = "farmer_adhar")
    private String farmerAdhar;
    @Column(name = "farmer_adharback")
    private String farmerAdharback;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "status")
    private Integer status;
    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @OneToMany(mappedBy = "farmerId")
    @JsonManagedReference
    private List<Junction> junctionList;
    @OneToMany(mappedBy = "fid")
    @JsonManagedReference
    private List<Product> productList;

    public FarmerRegister() {
    }

    public FarmerRegister(Integer fid) {
        this.fid = fid;
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

    public String getFarmerImg() {
        return farmerImg;
    }

    public void setFarmerImg(String farmerImg) {
        this.farmerImg = farmerImg;
    }

    public String getFarmerAdhar() {
        return farmerAdhar;
    }

    public void setFarmerAdhar(String farmerAdhar) {
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

    public List<Junction> getJunctionList() {
        return junctionList;
    }

    public void setJunctionList(List<Junction> junctionList) {
        this.junctionList = junctionList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getFarmerAdharback() {
        return farmerAdharback;
    }

    public void setFarmerAdharback(String farmerAdharback) {
        this.farmerAdharback = farmerAdharback;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister[ fid=" + fid + " ]";
    }

//    public void setRole(String role) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    private static class Waste {

        public Waste() {
        }
    }
}
