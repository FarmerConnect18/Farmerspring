/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.BiddingDTO;
import com.Farmer.Farmer_ConnectSP.DTOS.ProductDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Bidding;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import com.Farmer.Farmer_ConnectSP.Services.BiddingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
@CrossOrigin("*")
public class BiddingController {
    
    @Autowired
    private BiddingService bidservice;
    
    @PostMapping("/bidding-value")
    public ResponseEntity<BiddingDTO> BiddingValue(@RequestBody BiddingDTO biddto) {
        BiddingDTO obj = bidservice.Biddingvalue(biddto);
        
        if (obj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping("/rejectbid-value/{id}")
    public ResponseEntity<Bidding> rejectedBid(@PathVariable Integer id) {
        Bidding mess = bidservice.rejectBidprice(id);
        
        if (mess == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mess);
        
    }
    
    @GetMapping("/getcutomer-biddata/{id}")
    public ResponseEntity<List<BiddingDTO>> getcustomerbiddata(@PathVariable Integer id) {
        List<BiddingDTO> bidobj = bidservice.GetBiddingproducts(id);
        
        if (bidobj == null) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(bidobj);
    }
    
    @GetMapping("/getall-biddingdata")
    public ResponseEntity<List<BiddingDTO>> getallBdata() {
        List<BiddingDTO> bidobj = bidservice.Getallbiddingdata();
        
        if (bidobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bidobj);
    }
    
    @GetMapping("/getold-bidvalue/{id}")
    public ResponseEntity<?> getBiddingvalue(@PathVariable Integer id) {
        BiddingDTO biddto = bidservice.getBiddedvalue(id);
        
        if (biddto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(biddto);
    }
    
    @PutMapping("/replace-bidvalue/{id}")
    public ResponseEntity<BiddingDTO> replacedbidvalue(@RequestBody BiddingDTO biddto, @PathVariable Integer id) {
        BiddingDTO boj = bidservice.replacaebidvalue(biddto, id);
        
        if (boj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boj);
        
    }
    
    @GetMapping("/get-placedvalue/{id}")
    public ResponseEntity<?> placedValue(@PathVariable Integer id) {
        List<BiddingDTO> bidvaluelist = bidservice.biddingEnd(id);
        
        if (bidvaluelist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bidvaluelist);
    }
    
    @GetMapping("/endbidding-process/{id}")
    public ResponseEntity<SimpleMailMessage> endBiddingProcess(@PathVariable Integer id) {
        SimpleMailMessage message = bidservice.bidEndEmail(id);
        
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/getfarmer-history/{id}")
    public ResponseEntity<List<ProductDTO>> farmerHistory(@PathVariable Integer id) {
        List<ProductDTO> productlist = bidservice.getfarmerhistory(id);
        if (productlist == null) {
            return ResponseEntity.notFound().build();
            
        }
        
        return ResponseEntity.ok(productlist);
        
    }
    
    @GetMapping("/getfarmer-producthistory/{id}")
    public ResponseEntity<BiddingDTO> farmerProductHistory(@PathVariable Integer id) {
        BiddingDTO biddto = bidservice.getFarmerproducthistory(id);
        
        if (biddto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(biddto);
        
    }
    
    @GetMapping("/getcustomer-history/{id}")
    public ResponseEntity<List<BiddingDTO>> customerhistory(@PathVariable Integer id) {
        List<BiddingDTO> biddto = bidservice.getcustomerhistory(id);
        
        if (biddto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(biddto);
        
    }
    
    @GetMapping("/getcustomer-producthistory/{id}")
    public ResponseEntity<BiddingDTO> getCustomerProductHistory(@PathVariable Integer id)
    {
        System.out.println(id);
        
        BiddingDTO biddto=bidservice.getcustomerproducthistory(id);
        if(biddto==null)
        {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(biddto);
        
    }
}
