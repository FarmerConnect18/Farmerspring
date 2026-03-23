/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.cloudinary.Cloudinary;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudobj;

    public Map uploadimage(MultipartFile file, String foldername) {
        try {
            Map imgdata = cloudobj.uploader().upload(file.getBytes(), Map.of("folder", foldername));

            return imgdata;

        } catch (Exception e) {
            return null;
        }
    }
}
