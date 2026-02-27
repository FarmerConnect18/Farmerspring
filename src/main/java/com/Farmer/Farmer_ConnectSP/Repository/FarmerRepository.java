/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface FarmerRepository extends JpaRepository<FarmerRegister,Integer> {

    public FarmerRegister findByUsernameAndPassword(String name, String password);

    public FarmerRegister findByemail(String email);
    
}
