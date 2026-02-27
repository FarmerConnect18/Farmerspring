/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
@CrossOrigin("*")
@Controller
public class FarmerController {


    @Autowired
    private Farmerimage farmerservice;
//    private String localpath="E:\\Project\\Farmer-ConnectSP\\src\\main\\resources\\static";

    /**
     *
     * @param farmerdto
     * @return
     */
    @PostMapping("/farmer-login")
    public ResponseEntity<FarmerRegister> farmerlogin(@ModelAttribute FarmerDTO farmerdto) {
        System.out.println("API hit");
        FarmerRegister mess = farmerservice.findfarmer(farmerdto.getUsername(), farmerdto.getPassword());

        System.out.println(mess);
        if (mess != null) {
            System.out.println(mess.getUsername());
            return ResponseEntity.ok(mess);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    @PostMapping("/farmer-register")
    public ResponseEntity<?> farmerdata(@ModelAttribute FarmerDTO obj) {

        System.out.print("API hit");
        try {
            String s = farmerservice.inseruserdata(obj);
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {

            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    /**
     *
     * @return
     */
    @GetMapping("/farmer-list")
    public ResponseEntity<List<FarmerRegister>> farmerlist() {
        List<FarmerRegister> list = farmerservice.getallfarmer();

        if (list != null) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/farmer-getbyid/{id}")
    public ResponseEntity<FarmerRegister> farmegetbyid(@PathVariable Integer id) {
        FarmerRegister obj = farmerservice.getfarmerbyid(id);

        if (obj != null) {
            return ResponseEntity.ok(obj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param id
     * @param farmerdto
     * @return
     */
    @PutMapping("/update-farmer/{id}")
    public ResponseEntity<FarmerRegister> updatedata(@PathVariable Integer id, @ModelAttribute FarmerDTO farmerdto) {

        FarmerRegister farmobj = farmerservice.updatefamer(id, farmerdto);

        if (farmobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(farmobj);
    }
    
        @PutMapping("/update-farmerstatus/{id}")
    public ResponseEntity<FarmerRegister> updatestatus(@PathVariable Integer id, @ModelAttribute FarmerDTO farmerdto) {

        FarmerRegister farmobj =farmerservice.unblockfarmer(id, farmerdto);

        if (farmobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(farmobj);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("farmer/{id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable Integer id) {

        String mess = farmerservice.deletefarmer(id);
        return ResponseEntity.ok(mess);
    }

}
