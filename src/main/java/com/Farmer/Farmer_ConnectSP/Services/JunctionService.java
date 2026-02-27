/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.JunctionDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.Junction;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.JunctionRepository;
import java.io.File;
import java.io.IOException;
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
public class JunctionService {

    @Autowired
    private JunctionRepository junctionrepo;
    @Autowired
    private FarmerRepository farmerrepo;

    private final String folderpath = "E:/Project/projectimages/Junction-images/";
    private final String serverurl = "http://localhost:8080";

    public Junction addproduct(JunctionDTO junctiondto) {
        File folder = new File(folderpath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Junction junobj = new Junction();

        junobj.setAddress(junctiondto.getAddress());
        junobj.setCost(junctiondto.getCost());
        junobj.setDescription(junctiondto.getDescription());
        junobj.setJuncname(junctiondto.getJuncname());
        junobj.setPhoneno(junctiondto.getPhoneno());
        junobj.setStatus(0);

        String imagename;
        imagename = imagesave(junctiondto.getImg1());
        junobj.setImg1(imagename);

        imagename = imagesave(junctiondto.getImg2());
        junobj.setImg2(imagename);

        Optional<FarmerRegister> farmerop = farmerrepo.findById(junctiondto.getFarmerId());

        if (farmerop.isEmpty()) {
            return null;
        }

        FarmerRegister farmerobj = farmerop.get();

        junobj.setFarmerId(farmerobj);
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

    public List<Junction> alljunction() {
        List<Junction> junlist = junctionrepo.findAll();

        if (junlist.isEmpty()) {
            return null;
        }

        junlist.forEach(junimg -> {

            junimg.setImg1(serverurl + "/Junction-images/" + junimg.getImg1());
            junimg.setImg2(serverurl + "/Junction-images/" + junimg.getImg2());

        });
        return junlist;
    }

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
