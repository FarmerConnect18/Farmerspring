/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.JunctionDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.Junction;
import com.Farmer.Farmer_ConnectSP.Entities.JunctionRequest;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.JunctionRepository;
import com.Farmer.Farmer_ConnectSP.Repository.JunctionRequestRepository;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
@Service
public class JunctionService {
    
    @Autowired
    private JunctionRepository junctionrepo;
    @Autowired
    private FarmerRepository farmerrepo;
    @Autowired
    private JunctionRequestRepository junctionrequestrepo;
    @Autowired
    private JavaMailSender javamailsender;
    
    private final String folderpath = "E:/Project/projectimages/Junction-images/";
    private final String serverurl = "http://localhost:8080";
    
    public Junction addproduct(JunctionDTO junctiondto) {
        File folder = new File(folderpath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Optional<FarmerRegister> farmerop = farmerrepo.findById(junctiondto.getFarmerId());
        
        if (farmerop.isEmpty()) {
            return null;
        }
        FarmerRegister farmerobj = farmerop.get();
        
        Junction junobj = new Junction();
        
        junobj.setFarmerId(farmerobj);
        
        junobj.setAddress(junctiondto.getAddress());
        junobj.setCost(junctiondto.getCost());
        junobj.setDescription(junctiondto.getDescription());
        junobj.setJuncname(junctiondto.getJuncname());
        junobj.setPhoneno(farmerobj.getPhoneno());
        junobj.setStatus(1);
        
        String imagename;
        imagename = imagesave(junctiondto.getImg1());
        junobj.setImg1(imagename);
        
        imagename = imagesave(junctiondto.getImg2());
        junobj.setImg2(imagename);
        
        junctionrepo.save(junobj);
        
        return junobj;
    }
    
    public List<Junction> junctionByid(Integer farmerId) {
        List<Junction> junop = junctionrepo.findByFarmerId_Fid(farmerId);
        
        if (junop.isEmpty()) {
            return null;
        }
//        List<Junction> junobj = junop.get();

        junop.forEach(j -> {
            
            j.setImg1(serverurl + "/Junction-images/" + j.getImg1());
            j.setImg2(serverurl + "/Junction-images/" + j.getImg2());
            
        });
        
        return junop;
    }
    
    public List<JunctionDTO> alljunction() {
        List<Junction> junlist = junctionrepo.findAll();
        
        if (junlist.isEmpty()) {
            return null;
        }
        
        List<JunctionDTO> junctioddtolist = new ArrayList<>();
        
        for (Junction jun : junlist) {
            JunctionDTO jundto = new JunctionDTO();
            
            jundto.setAddress(jun.getAddress());
            jundto.setCost(jun.getCost());
            jundto.setPhoneno(jun.getPhoneno());
            jundto.setJid(jun.getJid());
            jundto.setJuncname(jun.getJuncname());
            jundto.setStatus(jun.getStatus());
            jundto.setDatetime(jun.getDatetime());
            jundto.setDescription(jun.getDescription());
            jundto.setFarmerId(jun.getFarmerId().getFid());
            
            jundto.setImage(serverurl + "/Junction-images/" + jun.getImg1());
            
            junctioddtolist.add(jundto);
            
        }
        
        return junctioddtolist;
    }

    //renting juction 
    public JunctionDTO rentjuction(JunctionDTO junnreobj) {
        JunctionRequest reobj = new JunctionRequest();
        
        Optional<Junction> junop = junctionrepo.findById(junnreobj.getJid());
        Junction junctionobj = junop.get();
        Optional<FarmerRegister> farmerop = farmerrepo.findById(junctionobj.getFarmerId().getFid());
        FarmerRegister farmerobj = farmerop.get();
        
        reobj.setFarmerId(farmerobj);
        reobj.setJunctionId(junctionobj);
        reobj.setStatus(1);
        
        junctionrequestrepo.save(reobj);
        
        Optional<FarmerRegister> farmop = farmerrepo.findById(reobj.getFarmerId().getFid());
        FarmerRegister famobj = farmop.get();
        //email sending for customer and farmer
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(farmerobj.getEmail());
        message.setSubject("🌾 Junction Equipment Rented Successfully");
        message.setText("Dear" + farmerobj.getUsername() + ",\n"
                + "\n"
                + "Greetings from Farmer-Connect 🌾\n"
                + "\n"
                + "✅ Your junction equipment has been successfully rented.\n"
                + "\n"
                + "📋 Rental Details:\n"
                + "\n"
                + "🚜 Junction Name:" + junctionobj.getJuncname() + "\n"
                + "👤 Rented By: " + famobj.getUsername() + "\n"
                + "📞 Buyer Contact:" + famobj.getPhoneno() + "\n"
                + "📅 Rental Date & Time: " + junctionobj.getDatetime() + "\n"
                + "💰 Rental Amount: ₹ " + junctionobj.getCost() + "\n"
                + "\n"
                + "Please ensure the equipment is ready and coordinate with the buyer if needed.\n"
                + "\n"
                + "Thank you for using Farmer-Connect 🌱\n"
                + "\n"
                + "Best Regards,\n"
                + "Farmer-Connect Team");
        javamailsender.send(message);

        //emai for renter
        message.setTo(famobj.getEmail());
        message.setSubject("🎉 Your Junction Rental is Confirmed");
        message.setText("Dear " + famobj.getUsername() + ",\n\n"
                + "Greetings from Farmer-Connect 🌾\n\n"
                + "✅ Your junction equipment has been successfully rented.\n\n"
                + "🚜 Junction Name: " + junctionobj.getJuncname() + "\n"
                + "👤 Rented By: " + farmerobj.getUsername() + "\n"
                + "💰 Rental Amount: ₹ " + junctionobj.getCost() + "\n\n"
                + "Thank you for using Farmer-Connect 🌱\n\n"
                + "Best Regards,\nFarmer-Connect Team");
        
        javamailsender.send(message);
        
        return junnreobj;
    }
    
    public List<JunctionDTO> getjuctionrequest(Integer fid) {
        List<Junction> junoplist = junctionrepo.findByFarmerId_Fid(fid);
        
        if (junoplist == null) {
            return null;
        }
        
        List<JunctionDTO> junctionlistdto = new ArrayList<>();
        
        for (Junction jun : junoplist) {
            JunctionDTO jundto = new JunctionDTO();
            jundto.setJuncname(jun.getJuncname());
            jundto.setDescription(jun.getDescription());
            jundto.setDatetime(jun.getDatetime());
            jundto.setJid(jun.getJid());
            jundto.setImage(serverurl + "/Junction-images/" + jun.getImg1());
            
            junctionlistdto.add(jundto);
            
        }
        
        return junctionlistdto;
    }
    
    @Transactional
    public List<String> deletejunctiond(Integer jid) {
        junctionrequestrepo.deleteByJunctionId_Jid(jid);
        junctionrepo.deleteById(jid);
        List<String> list = new ArrayList<>();
        list.add("Junction Deleted successfully........");
        return list;
    }

//    public JunctionDTO customerrequest(Integer jid) {
//        JunctionRequest junctiondobj = junctionrequestrepo.findByjunctionId_jid(jid);
//
//        Optional<Junction> junop = junctionrepo.findById(jid);
//        Junction junobj = junop.get();
//
//        Optional<FarmerRegister> farmerop = farmerrepo.findById(junctiondobj.getFarmerId().getFid());
//        FarmerRegister farmerobj = farmerop.get();
//
//        JunctionDTO jundto = new JunctionDTO();
//
//        jundto.setAddress(farmerobj.getAddress());
//        jundto.setCost(junobj.getCost());
//        jundto.setDatetime(junobj.getDatetime());
//        jundto.setDescription(junobj.getDescription());
//        jundto.setFarmerId(junobj.getFarmerId().getFid());
//        jundto.setJid(junobj.getJid());
//        jundto.setJuncname(junobj.getJuncname());
//        //farmer information
//        jundto.setPhoneno(farmerobj.getPhoneno());
//        jundto.setStatus(junobj.getStatus().intValue());
//        jundto.setCity(farmerobj.getCity());
//        jundto.setState(farmerobj.getState());
//        jundto.setFarmername(farmerobj.getUsername());
//
//        jundto.setImage(serverurl + "/Junction-images/" + junobj.getImg1());
//        jundto.setFarmerimage(serverurl + "/Farmer-photo/" + farmerobj.getFarmerImg());
//
//        return jundto;
//    }
    private String imagesave(MultipartFile file) {
        try {
            String image = file.getOriginalFilename();
            String imgextension = image.substring(image.lastIndexOf("."));
            String imagename = System.currentTimeMillis() + imgextension;
            
            Path path = Paths.get(folderpath, imagename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            return imagename;
        } catch (IOException e) {
            
            e.getMessage();
        }
        return null;
    }
}
