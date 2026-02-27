/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.Bidding;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface BiddingRepository extends JpaRepository<Bidding,Integer>{

    public List<Bidding> findByCustomerId_Cid(Integer cid);

    public List<Bidding> findByProductId_Pid(Integer pid);

    /**
     *
     * @param pid
     * @return
     */
//    public List<Product> findByProductId_Pid(Integer pid);
    
    
    
}
