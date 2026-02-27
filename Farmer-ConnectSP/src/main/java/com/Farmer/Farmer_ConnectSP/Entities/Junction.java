/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author preml
 */
@Entity
@Table(name = "junction")
@NamedQueries({
    @NamedQuery(name = "Junction.findAll", query = "SELECT j FROM Junction j"),
    @NamedQuery(name = "Junction.findByJid", query = "SELECT j FROM Junction j WHERE j.jid = :jid"),
    @NamedQuery(name = "Junction.findByJuncname", query = "SELECT j FROM Junction j WHERE j.juncname = :juncname"),
    @NamedQuery(name = "Junction.findByCost", query = "SELECT j FROM Junction j WHERE j.cost = :cost"),
    @NamedQuery(name = "Junction.findByPhoneno", query = "SELECT j FROM Junction j WHERE j.phoneno = :phoneno"),
    @NamedQuery(name = "Junction.findByDatetime", query = "SELECT j FROM Junction j WHERE j.datetime = :datetime"),
    @NamedQuery(name = "Junction.findByStatus", query = "SELECT j FROM Junction j WHERE j.status = :status")})
public class Junction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jid")
    private Integer jid;
    @Column(name = "juncname")
    private String juncname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "phoneno")
    private String phoneno;
    @Column(name="imag1")
    private String img1;
    @Column (name="img2")
    private String img2;
    @Lob
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date datetime;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "farmer_id", referencedColumnName = "fid")
    @ManyToOne
    @JsonBackReference
    private FarmerRegister farmerId;
    @OneToMany(mappedBy = "junctionId")
    private List<JunctionRequest> junctionRequestList;
    @OneToMany(mappedBy = "junctionId")
    private List<JunctionImg> junctionImgList;

    public Junction() {
    }

    public Junction(Integer jid) {
        this.jid = jid;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJuncname() {
        return juncname;
    }

    public void setJuncname(String juncname) {
        this.juncname = juncname;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
    
    
    public FarmerRegister getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(FarmerRegister farmerId) {
        this.farmerId = farmerId;
    }

    public List<JunctionRequest> getJunctionRequestList() {
        return junctionRequestList;
    }

    public void setJunctionRequestList(List<JunctionRequest> junctionRequestList) {
        this.junctionRequestList = junctionRequestList;
    }

    public List<JunctionImg> getJunctionImgList() {
        return junctionImgList;
    }

    public void setJunctionImgList(List<JunctionImg> junctionImgList) {
        this.junctionImgList = junctionImgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jid != null ? jid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Junction)) {
            return false;
        }
        Junction other = (Junction) object;
        if ((this.jid == null && other.jid != null) || (this.jid != null && !this.jid.equals(other.jid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.Junction[ jid=" + jid + " ]";
    }
    
}
