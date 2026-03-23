/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.JunctionDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Junction;
import com.Farmer.Farmer_ConnectSP.Services.JunctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
//@CrossOrigin("*")
public class JunctionController {

    @Autowired
    private JunctionService junservice;

    @PostMapping("/add-junction")
    public ResponseEntity<Junction> postjuction(@ModelAttribute JunctionDTO juncdto) {
        Junction junobj = junservice.addproduct(juncdto);

        if (junobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(junobj);

    }

    @GetMapping("/get-junction")
    public ResponseEntity<List<JunctionDTO>> getalljunction() {
        List<JunctionDTO> list = junservice.alljunction();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get-junction/{id}")
    public ResponseEntity<List<Junction>> getjunction(@PathVariable Integer id) {
        List<Junction> junobj = junservice.junctionByid(id);

        if (junobj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(junobj);
    }

    @PostMapping("/rent-junction/")
    public ResponseEntity<JunctionDTO> rentjunction(@ModelAttribute JunctionDTO jundto) {
        System.out.print("API hit here");
        JunctionDTO junmess = junservice.rentjuction(jundto);
        if (junmess == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(junmess);
    }

    @GetMapping("/getjunction-request/{id}")
    public ResponseEntity<List<JunctionDTO>> juctionrequest(@PathVariable Integer id) {
        List<JunctionDTO> junlist = junservice.getjuctionrequest(id);

        if (junlist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(junlist);
    }

    @DeleteMapping("/delete-junction/{id}")
    public ResponseEntity<List<String>> removejunction(@PathVariable Integer id) {
        List<String> list = junservice.deletejunctiond(id);
        if (list == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

//    @GetMapping("/getjunctionrequest-detail/{id}")
//    public ResponseEntity<JunctionDTO> requestdetail(@PathVariable Integer id) {
//        JunctionDTO jundto = junservice.customerrequest(id);
//        
//        if (jundto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(jundto);
//    }
}
