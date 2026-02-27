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
@Table(name = "junction_request")
@NamedQueries({
    @NamedQuery(name = "JunctionRequest.findAll", query = "SELECT j FROM JunctionRequest j"),
    @NamedQuery(name = "JunctionRequest.findByRid", query = "SELECT j FROM JunctionRequest j WHERE j.rid = :rid"),
    @NamedQuery(name = "JunctionRequest.findByDays", query = "SELECT j FROM JunctionRequest j WHERE j.days = :days"),
    @NamedQuery(name = "JunctionRequest.findByDatetime", query = "SELECT j FROM JunctionRequest j WHERE j.datetime = :datetime")})
public class JunctionRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rid")
    private Integer rid;
    @Column(name = "days")
    private Integer days;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @JoinColumn(name = "customer_id", referencedColumnName = "cid")
    @ManyToOne
    private CustomerRegister customerId;
    @JoinColumn(name = "junction_id", referencedColumnName = "jid")
    @ManyToOne
    private Junction junctionId;

    public JunctionRequest() {
    }

    public JunctionRequest(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public CustomerRegister getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerRegister customerId) {
        this.customerId = customerId;
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
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JunctionRequest)) {
            return false;
        }
        JunctionRequest other = (JunctionRequest) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Farmer.Farmer_ConnectSP.Entities.JunctionRequest[ rid=" + rid + " ]";
    }
    
}
