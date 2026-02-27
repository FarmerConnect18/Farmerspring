/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface CustomerRepository extends JpaRepository<CustomerRegister,Integer>{

//    public Optional<String> findByUsername(String username);
//
//    public Optional<List<String>> findByUsername(String username, String password);
//
//    public void findByUsernmaeAndPassword(String username, String password);

    public CustomerRegister findByUsernameAndPassword(String username, String password);

    public CustomerRegister findByemail(String email);
    
}
