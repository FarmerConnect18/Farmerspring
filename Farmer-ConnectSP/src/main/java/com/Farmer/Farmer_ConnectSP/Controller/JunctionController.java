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
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
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
    public ResponseEntity<List<Junction>> getalljunction() {
        List<Junction> list = junservice.alljunction();

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
}
