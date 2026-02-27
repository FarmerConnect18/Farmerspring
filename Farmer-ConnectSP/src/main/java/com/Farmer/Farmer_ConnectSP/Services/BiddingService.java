/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.BiddingDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Bidding;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import com.Farmer.Farmer_ConnectSP.Repository.BiddingRepository;
import com.Farmer.Farmer_ConnectSP.Repository.CustomerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.ProductRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class BiddingService {
    
    @Autowired
    private BiddingRepository biddingrepo;
    
    @Autowired
    private CustomerRepository customerrepo;
    
    @Autowired
    private ProductRepository productrepo;
    
    private final String serverurl = "http://localhost:8080";
    
    public BiddingDTO Biddingvalue(BiddingDTO biddto) {
        try {
            Optional<CustomerRegister> customerop = customerrepo.findById(biddto.getCustomerId());
            Optional<Product> productop = productrepo.findById(biddto.getProductId());
            if (customerop.isEmpty()) {
                return null;
            }
            
            if (productop.isEmpty()) {
                return null;
            }
            
            CustomerRegister customerobj = customerop.get();
            Product productobj = productop.get();
            
            Bidding bidobj = new Bidding();
            bidobj.setBiddingPrice(biddto.getBiddingPrice());
            bidobj.setCustomerId(customerobj);
            bidobj.setProductId(productobj);
            bidobj.setStatus(1);
            
            biddingrepo.save(bidobj);
            
            biddto.setBid(bidobj.getBid());
            biddto.setStatus(bidobj.getStatus());
            biddto.setDatetime(bidobj.getDatetime());
            return biddto;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String Placedbidvalues(Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);
        
        if (bidop.isEmpty()) {
            new RuntimeException("not values");
        }
        
        biddingrepo.deleteById(id);
        return "Rejected successfuly";
        
    }
    
    public List<BiddingDTO> GetBiddingproducts(Integer cid) {
        List<Bidding> bidobj = biddingrepo.findByCustomerId_Cid(cid);
        
        List<BiddingDTO> dtolist = new ArrayList<>();
        
        for (Bidding bid : bidobj) {
            BiddingDTO bidto = new BiddingDTO();
            bidto.setBid(bid.getBid());
            bidto.setBiddingPrice(bid.getBiddingPrice());
            bidto.setCustomerId(bid.getCustomerId().getCid());
            bidto.setProductId(bid.getProductId().getPid());
            bidto.setStatus(bid.getStatus());
            bidto.setDatetime(bid.getDatetime());
            Optional<Product> productop = productrepo.findById(bid.getProductId().getPid());
            if (productop.isEmpty()) {
                new RuntimeException("Empty products");
            }
            Product pobj = productop.get();
            bidto.setImg1(serverurl + "/Product-images/" + pobj.getImg1());
            bidto.setImg2(serverurl + "/Product-images/" + pobj.getImg2());
            
            dtolist.add(bidto);
        }
        return dtolist;
    }
    
    public List<BiddingDTO> Getallbiddingdata() {
        List<Bidding> objlist = biddingrepo.findAll();
        
        List<BiddingDTO> bidinglist = new ArrayList<>();
        
        for (Bidding bdto : objlist) {
            BiddingDTO bid = new BiddingDTO();
            
            bid.setBid(bdto.getBid());
            bid.setBiddingPrice(bdto.getBiddingPrice());
            bid.setDatetime(bdto.getDatetime());
            bid.setStatus(bdto.getStatus());
            bid.setCustomerId(bdto.getCustomerId().getCid());
            bid.setProductId(bdto.getProductId().getPid());
            
            Optional<Product> productop = productrepo.findById(bid.getProductId());
            if (productop.isEmpty()) {
                new RuntimeException("Empty products");
            }
            Product pobj = productop.get();
            bid.setImg1(serverurl + "/Product-images/" + pobj.getImg1());
            bid.setImg2(serverurl + "/Product-images/" + pobj.getImg2());
            
            bidinglist.add(bid);
        }
        
        return bidinglist;
        
    }
    
    public BiddingDTO getBiddedvalue(Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);
        
        if (bidop.isEmpty()) {
            new RuntimeException("Empty Biiding values");
        }
        
        Bidding bidobj = bidop.get();
        
        Optional<Product> productop = productrepo.findById(bidobj.getProductId().getPid());
        
        Product productobj = productop.get();
        BiddingDTO biddto = new BiddingDTO();
        
        biddto.setBid(id);
        biddto.setBiddingPrice(bidobj.getBiddingPrice());
        biddto.setDatetime(bidobj.getDatetime());
        biddto.setProductId(productobj.getPid());
        biddto.setCustomerId(bidobj.getCustomerId().getCid());
        biddto.setStatus(bidobj.getStatus());
        
        biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());
        biddto.setImg2(serverurl + "/Product-images/" + productobj.getImg2());
        
        return biddto;
        
    }
    
    public BiddingDTO replacaebidvalue(BiddingDTO biddto, Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);
        
        Bidding bidobj = bidop.get();
        
        bidobj.setBiddingPrice(biddto.getBiddingPrice());
        
        biddingrepo.save(bidobj);
        return biddto;
        
    }
    
    public List<BiddingDTO> biddingEnd(Integer pid) {
        List<Bidding> biddinglist = biddingrepo.findByProductId_Pid(pid);
        
        List<BiddingDTO> biddtolist = new ArrayList<>();
        
        for (Bidding product : biddinglist) {
            BiddingDTO biddto = new BiddingDTO();
            
            biddto.setBid(product.getBid());
            biddto.setBiddingPrice(product.getBiddingPrice());
            biddto.setDatetime(product.getDatetime());
            biddto.setProductId(product.getProductId().getPid());
            biddto.setStatus(product.getStatus());
            biddto.setCustomerId(product.getCustomerId().getCid());
            
            Optional<CustomerRegister> customerop = customerrepo.findById(product.getCustomerId().getCid());
            CustomerRegister customerobj = customerop.get();
            biddto.setCustomername(customerobj.getUsername());
            
            Optional<Product> productop = productrepo.findById(product.getProductId().getPid());
            Product productobj = productop.get();
            biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());
            
            biddtolist.add(biddto);
            
        }
        return biddtolist;
        
    }
}
