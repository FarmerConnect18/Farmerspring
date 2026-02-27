/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findByFid_Fid(Integer fid);




  
    
}
