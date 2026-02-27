/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Entities;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author preml
 */
@Entity
@Table(name = "junction_img")
@NamedQueries({
    @NamedQuery(name = "JunctionImg.findAll", query = "SELECT j FROM JunctionImg j"),
    @NamedQuery(name = "JunctionImg.findByImgid", query = "SELECT j FROM JunctionImg j WHERE j.imgid = :imgid"),
    @NamedQuery(name = "JunctionImg.findByImgName", query = "SELECT j FROM JunctionImg j WHERE j.imgName = :imgName"),
    @NamedQuery(name = "JunctionImg.findByDatetime", query = "SELECT j FROM JunctionImg j WHERE j.datetime = :datetime")})
public class JunctionImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "imgid")
    private Integer imgid;
    @Column(name = "img_name")
    private String imgName;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @JoinColumn(name = "junction_id", referencedColumnName = "jid")
    @ManyToOne
    private Junction junctionId;

    public JunctionImg() {
    }

    public JunctionImg(Integer imgid) {
        this.imgid = imgid;
    }

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Junction getJunctionId() {
        return junctionId;
    }

    public void setJunctionId(Junction junctionId) {
        this.junctionId = junctionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgid != null ? imgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JunctionImg)) {
            return false;
        }
        JunctionImg other = (JunctionImg) object;
        if ((this.imgid == null && other.imgid != null) || (this.imgid != null && !this.imgid.equals(other.imgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.JunctionImg[ imgid=" + imgid + " ]";
    }
    
}
