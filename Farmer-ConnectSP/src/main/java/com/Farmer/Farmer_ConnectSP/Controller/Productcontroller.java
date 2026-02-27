/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.ProductDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import com.Farmer.Farmer_ConnectSP.Services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class Productcontroller {
    
    @Autowired
    private ProductService productservice;
    
    @PostMapping("/add-product")
    public ResponseEntity<Product> postproduct(@ModelAttribute ProductDTO dtopobj) {
        System.out.println(dtopobj.getFarmerId());
        Product obj = productservice.inserproduct(dtopobj);
        
        if (obj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(obj);
    }
    
    @GetMapping("/get-product")
    public ResponseEntity<List<Product>> getproduct() {
        List<Product> list = productservice.getalproduct();
        if (list == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> productbyid(@PathVariable Integer id) {
        Product productobj = productservice.getproductbyid(id);
        
        if (productobj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productobj);
    }
    
    @PutMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteproduct(@PathVariable Integer id) {
        String mess = productservice.deletebyid(id);
        if (mess == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mess);
    }
    
    @GetMapping("/getfarmer-product/{id}")
    public ResponseEntity<List<ProductDTO>> getfarmerprolist(@PathVariable Integer id)
    {
        List<ProductDTO> productlist=productservice.gerfarmerproduct(id);
        if(productlist==null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productlist);
    }
}
