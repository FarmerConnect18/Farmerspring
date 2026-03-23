/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.EmailDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Admin;
import com.Farmer.Farmer_ConnectSP.Services.Emailservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Farmer.Farmer_ConnectSP.Repository.AdminRepository;
import com.Farmer.Farmer_ConnectSP.Services.AdminService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author preml
 */
@RestController
@Controller
//@CrossOrigin("*")
public class AdminController {
    
    @Autowired
    private AdminRepository adminrepo;
    
    @Autowired
    private AdminService adminserice;
    
    @Autowired
    private Emailservice emailservice;

    /**
     *
     * @param obj
     * @return
     */
//    @PostMapping("/login-admin")
//    public ResponseEntity<Admin> smaple(@RequestBody Admin obj) {
//        adminrepo.save(obj);
//        return ResponseEntity.ok().body(obj);
//    }
    @PostMapping("/admin-login/")
    public ResponseEntity<Admin> adminlgon(@RequestBody Admin obj) {
        Admin adminobj = adminserice.getAdmin(obj.getPassword(), obj.getUsername());
        if (adminobj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adminobj);
    }
    
    @PostMapping("/create-admin")
    public ResponseEntity<EmailDTO> admincreate(@RequestBody EmailDTO email) {
        EmailDTO adminobj = adminserice.createAdmin(email);
        
        if (adminobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adminobj);
        
    }
    
    @GetMapping("/get-admin/")
    public ResponseEntity<List<Admin>> userdata() {
        List<Admin> list = adminrepo.findAll();
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/email-Sending-farmer/{id}")
    public ResponseEntity<SimpleMailMessage> ApprovetoFarmer(@PathVariable Integer id) {
        SimpleMailMessage mess = emailservice.sendmailtofarmer(id);
        
        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mess);
    }
    
    @GetMapping("/email-Sending-customer/{id}")
    public ResponseEntity<SimpleMailMessage> ApprovetoCustomer(@PathVariable Integer id) {
        SimpleMailMessage mess = emailservice.sendmailtocustomer(id);
        
        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mess);
    }
    
    @GetMapping("/block-farmer/{id}")
    public ResponseEntity<SimpleMailMessage> blocktofarmer(@PathVariable Integer id) {
        SimpleMailMessage mess = emailservice.blockfarmer(id);
        
        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(mess);
        
    }
    
    @GetMapping("/block-customer/{id}")
    public ResponseEntity<SimpleMailMessage> blocktocustomer(@PathVariable Integer id) {
        SimpleMailMessage mess = emailservice.blockcustomer(id);
        
        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(mess);
        
    }
    
    @PostMapping("/adminemail-verify")
    public ResponseEntity<EmailDTO> adminEmailVerify(@RequestBody EmailDTO emaildto) {
        EmailDTO optdto = adminserice.adminemailverify(emaildto);
        if (optdto == null) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(optdto);
        
    }
    
    @PostMapping("/adminpassword-update")
    public ResponseEntity<EmailDTO> adminPassUpdate(@RequestBody EmailDTO emaildto) {
        EmailDTO passupdateildto = adminserice.passwordupdate(emaildto);
        
        if (passupdateildto == null) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(passupdateildto);
    }
}
