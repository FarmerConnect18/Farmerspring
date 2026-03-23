/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.Bidding;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface BiddingRepository extends JpaRepository<Bidding,Integer>{

    public List<Bidding> findByCustomerId_Cid(Integer cid);

    public List<Bidding> findByProductId_Pid(Integer pid);

    public Bidding findByProductId_PidAndStatus(Integer pid,Integer status);

    public List<Bidding> findByCustomerId_CidAndStatus(Integer cid, int i);

    public void deleteByProductId_PidAndStatus(Integer pid, Integer status);



    /**
     *
     * @param pid
     * @return
     */
//    public List<Product> findByProductId_Pid(Integer pid);
    
    
    
}
