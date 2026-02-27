/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.ProductDTO;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.ProductRepository;
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
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author preml
 */
@Service
public class ProductService {

    private String folderpath = "E:/Project/projectimages/Product-images/";
    private final String serverurl = "http://localhost:8080";

    @Autowired
    private ProductRepository productrepo;

    @Autowired
    private FarmerRepository farmerrepo;

    public Product inserproduct(ProductDTO productdto) {

        try {
            File folder = new File(folderpath);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            Product obj = new Product();

            obj.setPrice(productdto.getPrice());
            obj.setProductName(productdto.getProductName());
            obj.setProductType(productdto.getProductType());
            obj.setQuantity(productdto.getQuantity());
            obj.setQuality(productdto.getQuality());
            obj.setStatus(0);
            obj.setDescription(productdto.getDescription());
            String imagename;
            imagename = imagesave(productdto.getImg1());
            obj.setImg1(imagename);

            imagename = imagesave(productdto.getImg2());
            obj.setImg2(imagename);

            Optional<FarmerRegister> farmerop = farmerrepo.findById(productdto.getFarmerId());

            if (farmerop.isEmpty()) {
                return null;
            }
            FarmerRegister farmerobj = farmerop.get();
            obj.setFid(farmerobj);

//            Product productobj = productrepo.save(obj);
//            ProductDTO prodto = new ProductDTO();
//            prodto.setFarmerId(productobj.getFid().getFid());
//            prodto.setProductName(productobj.getProductName());


            return productrepo.save(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String imagesave(MultipartFile file) throws IOException {
        String imgname = file.getOriginalFilename();
        String extenstion = imgname.substring(imgname.lastIndexOf("."));
        String uniquename = System.currentTimeMillis() + extenstion;

        Path path = Paths.get(folderpath, uniquename);

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return uniquename;
    }

    public List<Product> getalproduct() {
        List<Product> productlist = productrepo.findAll();

        productlist.forEach(products -> {

            if (products.getImg1() != null) {
                products.setImg1(serverurl + "/Product-images/" + products.getImg1());
            }

            if (products.getImg2() != null) {
                products.setImg2(serverurl + "/Product-images/" + products.getImg2());
            }
        });
        return productlist;
    }

    public Product getproductbyid(Integer id) {
        Optional<Product> productop = productrepo.findById(id);

        if (productop.isEmpty()) {
            return null;
        }
        Product productobj = productop.get();

        productobj.setImg1(serverurl + "/Product-images/" + productobj.getImg1());
        productobj.setImg2(serverurl + "/Product-images/" + productobj.getImg2());
        return productobj;
    }

    public String deletebyid(Integer id) {
        Optional<Product> productop = productrepo.findById(id);

        Product productobj = productop.get();

        if (productobj.getImg1() != null) {
            File path = new File(serverurl + productobj.getImg1());

            if (path.exists()) {
                path.delete();
            }
        }

        if (productobj.getImg2() != null) {
            File path = new File(serverurl + productobj.getImg2());

            if (path.exists()) {
                path.delete();
            }
        }
        productrepo.deleteById(id);
        return "Delete successfully";
    }
    
    public List<ProductDTO> gerfarmerproduct(Integer fid)
    {
        List<Product> productlist=productrepo.findByFid_Fid(fid);
        
        List<ProductDTO> productdtolist=new ArrayList<>();
        if(productlist.isEmpty())
        {
            return null;
        }
        
        
        productlist.forEach(prolist->{
        
            ProductDTO prodto=new ProductDTO();
            prodto.setDatetime(prolist.getDatetime());
            prodto.setDescription(prolist.getDescription());
            prodto.setProductName(prolist.getProductName());
            prodto.setProductType(prolist.getProductType());
            prodto.setPid(prolist.getPid());
            prodto.setStatus(prolist.getStatus());
            prodto.setPrice(prolist.getPrice());
            prodto.setImgname1(serverurl + "/Product-images/" +prolist.getImg1());
            productdtolist.add(prodto);
        });
        return productdtolist;
    }
}
