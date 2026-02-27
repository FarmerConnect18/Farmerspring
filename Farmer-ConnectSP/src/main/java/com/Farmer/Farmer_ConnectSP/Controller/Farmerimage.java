/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
@Service
public class Farmerimage {
    
    @Autowired
    private FarmerRepository farmerrepository;

//    @Value("${project.image}")
//    private  String folderpath="E:/Project/Farmer-ConnectSP/src/main/resources/static/Farmer-photo";
    private final String folderpath = "E:/Project/projectimages/Farmer-photo/";
    private final String serverurl = "http://localhost:8080";

    /**
     *
     * @param farmerdto
     * @return
     */
    public String inseruserdata(FarmerDTO farmerdto) {
        try {
            File folder = new File(folderpath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
//-----------------------------profile image -----------------------------------
//            MultipartFile file = farmerdto.getFarmerImg();
//            String origanlname = file.getOriginalFilename();
//            String extention = origanlname.substring(origanlname.indexOf("."));
//            String filename = System.currentTimeMillis() + extention;
//            InputStream inputstream;
//
//            java.nio.file.Path filepath = Paths.get(folderpath, filename);
//
//            Files.copy(inputstream, filepath, StandardCopyOption.REPLACE_EXISTING);
//
//            MultipartFile adharfront = farmerdto.getFarmerAdhar();
//            String orgadharname = adharfront.getOriginalFilename();
//            String extenstion = orgadharname.substring(orgadharname.indexOf("."));
//            String fontname = System.currentTimeMillis() + extenstion;
//            inputstream = adharfront.getInputStream();
//            java.nio.file.Path adharpath = Paths.get(folderpath, fontname);
            ////            File adharfile=new File(folderpath,adharfront);
////            Path adharpath=adharfile.getPath()                  
//            Files.copy(inputstream, adharpath, StandardCopyOption.REPLACE_EXISTING);
//
//            MultipartFile adharback = farmerdto.getFarmerAdharback();
//            String adharbackganlname = adharback.getOriginalFilename();
//            String extention3 = adharbackganlname.substring(adharbackganlname.indexOf("."));
//            String adharbackname = System.currentTimeMillis() + extention3;
//            inputstream = adharback.getInputStream();
//
//            java.nio.file.Path filepath3 = Paths.get(folderpath, adharbackname);
//
//            Files.copy(inputstream, filepath3, StandardCopyOption.REPLACE_EXISTING);

            FarmerRegister farmerobj = new FarmerRegister();
            farmerobj.setAddress(farmerdto.getAddress());
            farmerobj.setCity(farmerdto.getCity());
            farmerobj.setEmail(farmerdto.getEmail());
//            farmerobj.setFarmerAdhar(filename);
            farmerobj.setPassword(farmerdto.getPassword());
            farmerobj.setPhoneno(farmerdto.getPhoneno());
            farmerobj.setState(farmerdto.getState());
            farmerobj.setUsername(farmerdto.getUsername());
            farmerobj.setStatus(farmerdto.getStatus());
            farmerobj.setDatetime(farmerdto.getDatetime());
            farmerobj.setRole(farmerdto.getRole());
//            farmerobj.setFarmerImg(filename);
            String imag1;
            String imag2;
            imag1 = imagesave(farmerdto.getFarmerAdhar());
            imag2 = imagesave(farmerdto.getFarmerAdharback());
            farmerobj.setFarmerAdhar(imag1);
            farmerobj.setFarmerAdharback(imag2);
            FarmerRegister mess = farmerrepository.save(farmerobj);
            
            return "inserted successfully";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public List<FarmerRegister> getallfarmer() {
        List<FarmerRegister> farmer = farmerrepository.findAll();
        farmer.forEach(farm -> {
            farm.setFarmerImg(
                    serverurl + "/Farmer-photo/" + farm.getFarmerImg());
            farm.setFarmerAdhar(
                    serverurl + "/Farmer-photo/" + farm.getFarmerAdhar());
            farm.setFarmerAdharback(
                    serverurl + "/Farmer-photo/" + farm.getFarmerAdharback());
        });
        return farmer;
    }
    
    public FarmerRegister getfarmerbyid(Integer id) {
        Optional<FarmerRegister> farmerop = farmerrepository.findById(id);
        
        if (farmerop.isEmpty()) {
            return null;
        }
        
        FarmerRegister farmerobj = farmerop.get();
        
        farmerobj.setFarmerAdhar(serverurl + "/Farmer-photo/" + farmerobj.getFarmerAdhar());
        farmerobj.setFarmerAdharback(serverurl + "/Farmer-photo/" + farmerobj.getFarmerAdharback());
        farmerobj.setFarmerImg(serverurl + "/Farmer-photo/" + farmerobj.getFarmerImg());
        return farmerobj;
    }
    
    public FarmerRegister updatefamer(Integer id, FarmerDTO farmerdto) {
        Optional<FarmerRegister> farmerop = farmerrepository.findById(id);
        
        if (farmerop.isEmpty()) {
            return null;
        }
        
        FarmerRegister farmerobj = farmerop.get();
        
        farmerobj.setAddress(farmerdto.getAddress());
        farmerobj.setCity(farmerdto.getCity());
        farmerobj.setDatetime(farmerdto.getDatetime());
        farmerobj.setEmail(farmerdto.getEmail());
        farmerobj.setPassword(farmerdto.getPassword());
        farmerobj.setStatus(farmerdto.getStatus());
        farmerobj.setState(farmerdto.getState());
        farmerobj.setUsername(farmerdto.getUsername());
        
        try {
            if (farmerdto.getFarmerImg() != null) {
                MultipartFile image = farmerdto.getFarmerImg();
                String imgname = image.getOriginalFilename();
                String imgexten = imgname.substring(imgname.indexOf("."));
                
                String proimgname = System.currentTimeMillis() + imgexten;
                Path path = Paths.get(folderpath, proimgname);
                Files.copy(farmerdto.getFarmerImg().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                
                farmerobj.setFarmerImg(proimgname);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//       farmerobj.setFarmerAdhar(serverurl+"/Farmer-photo/"+farmerdto.getFarmerAdhar());
//       farmerobj.setFarmerAdharback(serverurl+"/Farmer-photo/"+farmerdto.getFarmerAdharback());
        FarmerRegister save = farmerrepository.save(farmerobj);
        return save;
        
    }
    
    public String deletefarmer(Integer id) {
//        boolean status=false;

        Optional<FarmerRegister> farmerop = farmerrepository.findById(id);
        
        if (farmerop.isEmpty()) {
            return null;
        }
        
        FarmerRegister farmerobj = farmerop.get();
        
        if (farmerobj.getFarmerAdhar() != null) {
            File path = new File(serverurl + farmerobj.getFarmerAdhar());
            
            if (path.exists()) {
                path.delete();
            }
        }
        
        if (farmerobj.getFarmerAdharback() != null) {
            File path = new File(serverurl + farmerobj.getFarmerAdharback());
            
            if (path.exists()) {
                path.delete();
            }
        }
        farmerrepository.deleteById(id);
        return "Deleted successfuly";
    }
    
    public String imagesave(MultipartFile file) {
        try {
            String image = file.getOriginalFilename();
            String imgextension = image.substring(image.lastIndexOf("."));
            String imagename = System.currentTimeMillis() + imgextension;
            
            Path path = Paths.get(folderpath, imagename);
            
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            return imagename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public FarmerRegister unblockfarmer(Integer id, FarmerDTO farmerdto) {
        
        Optional<FarmerRegister> farmerop = farmerrepository.findById(id);
        
        if (farmerop.isEmpty()) {
            return null;
        }
        FarmerRegister farmerobj = farmerop.get();
        
        farmerobj.setStatus(farmerdto.getStatus());
        
        farmerrepository.save(farmerobj);
        return farmerobj;        
    }
    
    public FarmerRegister findfarmer(String name, String password) {
        FarmerRegister save = farmerrepository.findByUsernameAndPassword(name, password);
        
        if (save != null) {
            save.setUsername(name);
            save.setPassword(password);
            return save;
        }
        return save;
    }
}
