/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.JunctionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface JunctionRequestRepository extends JpaRepository<JunctionRequest,Integer>{

    public JunctionRequest findByjunctionId_jid(Integer jid);

    public void deleteByJunctionId_Jid(Integer jid);
    
}
