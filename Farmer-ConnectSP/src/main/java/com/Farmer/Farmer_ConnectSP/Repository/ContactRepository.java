/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    
}
