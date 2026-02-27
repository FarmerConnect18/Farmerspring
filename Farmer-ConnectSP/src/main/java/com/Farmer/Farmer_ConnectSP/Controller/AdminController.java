/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.CustomerDTO;
import com.Farmer.Farmer_ConnectSP.DTOS.EmailDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Admin;
import com.Farmer.Farmer_ConnectSP.Repository.AdminInterface;
import com.Farmer.Farmer_ConnectSP.Services.Emailservice;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
@Controller
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminInterface adminrepo;

    @Autowired
    private Emailservice emailservice;

    /**
     *
     * @param obj
     * @return
     */
    @PostMapping("/login-admin")
    public ResponseEntity<Admin> smaple(@RequestBody Admin obj) {
        obj.setCreatedAt(LocalDateTime.now());
        adminrepo.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/get-admin")
    public ResponseEntity<List<Admin>> userdata() {
        List<Admin> list = adminrepo.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/email-Sending-farmer")
    public ResponseEntity<SimpleMailMessage> ApprovetoFarmer(@ModelAttribute FarmerDTO farmerdto) {
        SimpleMailMessage mess = emailservice.sendmailtofarmer(farmerdto);

        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mess);
    }

    @PostMapping("/email-Sending-customer")
    public ResponseEntity<SimpleMailMessage> ApprovetoCustomer(@ModelAttribute CustomerDTO customerdto) {
        SimpleMailMessage mess = emailservice.sendmailtocustomer(customerdto);

        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mess);
    }

    
    @PostMapping("/block-farmer/")
    public ResponseEntity<SimpleMailMessage> blocktofarmer(@ModelAttribute FarmerDTO farmerdto) {
        SimpleMailMessage mess = emailservice.blockfarmer(farmerdto);

        if (mess == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mess);

    }

    @PostMapping("/block-customer/")
    public ResponseEntity<SimpleMailMessage> blocktocustomer(@ModelAttribute CustomerDTO customerdto) {
        SimpleMailMessage mess = emailservice.blockcustomer(customerdto);

        if (mess == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mess);

    }
}
