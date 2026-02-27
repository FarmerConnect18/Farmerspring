/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Repository;

import com.Farmer.Farmer_ConnectSP.Entities.Junction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author preml
 */
public interface JunctionRepository extends JpaRepository<Junction, Integer> {

//    public Optional<Junction> findByFarmerID(Integer farmerId);
//
//    public Optional<Junction> findByFarmer_Id(Integer farmerId);

    public List<Junction> findByFarmerId_Fid(Integer farmerId);

}
