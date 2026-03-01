/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.Entities.Admin;
import com.Farmer.Farmer_ConnectSP.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminrepo;
    
    public Admin getAdmin(String Password,String Username)
    {
       Admin obj=adminrepo.findByUsernameAndPassword(Username,Password);
       
       if(obj==null)
       {
           new RuntimeException("admin data not found");
       }
       Admin adminobj=new Admin();
       adminobj.setAid(obj.getAid());
       adminobj.setPassword(obj.getPassword());
       adminobj.setUsername(obj.getUsername());
       
       return adminobj;
       
    }
    
    
}
