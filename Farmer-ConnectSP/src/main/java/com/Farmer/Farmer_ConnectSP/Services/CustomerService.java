/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.Controller.FarmerDTO;
import com.Farmer.Farmer_ConnectSP.DTOS.CustomerDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Repository.CustomerRepository;
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
public class CustomerService {

    @Autowired
    private CustomerRepository customerrepository;

    private final String folderpath = "E:/Project/projectimages/Customer-images/";
    private final String serverurl = "http://localhost:8080";

    public CustomerRegister addcustomer(CustomerDTO customerdto) {
        File folder = new File(folderpath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        CustomerRegister customerobj = new CustomerRegister();

        customerobj.setAddress(customerdto.getAddress());
        customerobj.setCity(customerdto.getCity());
        customerobj.setDatetime(customerdto.getDatetime());
        customerobj.setEmail(customerdto.getEmail());
        customerobj.setPassword(customerdto.getPassword());
        customerobj.setPhoneno(customerdto.getPhoneno());
        customerobj.setState(customerdto.getState());
        customerobj.setStatus(0);
        customerobj.setUsername(customerdto.getUsername());

        String frontimg = imagesave(customerdto.getCustomerAdhar());
        String backimg = imagesave(customerdto.getCustomerAdharback());

        customerobj.setCustomerAdhar(frontimg);
        customerobj.setCustomerAdharback(backimg);

        customerrepository.save(customerobj);
        return customerobj;
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

    public List<CustomerRegister> getcustomerlist() {
        List<CustomerRegister> customerlist = customerrepository.findAll();

        customerlist.forEach(customer -> {

            customer.setCustomerAdhar(
                    serverurl + "/Customer-images/" + customer.getCustomerAdhar());

            customer.setCustomerAdharback(
                    serverurl + "/Customer-images/" + customer.getCustomerAdharback());
        });
        return customerlist;
    }

    public CustomerRegister getcustomerbyid(Integer id) {
        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }

        CustomerRegister customerobj = customerop.get();

        customerobj.setCustomerAdhar(serverurl + "/Customer-images/" + customerobj.getCustomerAdhar());
        customerobj.setCustomerAdharback(serverurl + "/Customer-images/" + customerobj.getCustomerAdharback());
        customerobj.setCustomerImg(serverurl + "/Customer-images/" + customerobj.getCustomerImg());

        return customerobj;
    }

    public String updatecustomer(Integer id, CustomerDTO customerdto) {
        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }

        CustomerRegister customerobj = customerop.get();

        customerobj.setAddress(customerdto.getAddress());
        customerobj.setCity(customerdto.getCity());
        customerobj.setDatetime(customerdto.getDatetime());
        customerobj.setPassword(customerdto.getPassword());
        customerobj.setPhoneno(customerdto.getPhoneno());
        customerobj.setState(customerdto.getState());
        customerobj.setStatus(customerdto.getStatus());
        customerobj.setEmail(customerdto.getEmail());
        customerobj.setUsername(customerdto.getUsername());

        String image = imagesave(customerdto.getCustomerAdhar());
        customerobj.setCustomerAdhar(image);
        image = imagesave(customerdto.getCustomerAdharback());
        customerobj.setCustomerAdharback(image);
        image = imagesave(customerdto.getCustomerImg());
        customerobj.setCustomerImg(image);
        customerrepository.save(customerobj);
        return "Updated successfully";
    }

    public CustomerRegister unblockcustomer(Integer id, CustomerDTO customerdto) {

        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }
        CustomerRegister customerobj = customerop.get();

        customerobj.setStatus(customerdto.getStatus());

        customerrepository.save(customerobj);
        return customerobj;
    }

    public String deletecustomer(Integer id) {
        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }

        CustomerRegister customerobj = customerop.get();

        if (customerobj.getCustomerAdhar() != null) {
            File path = new File(serverurl + customerobj.getCustomerAdhar());

            if (path.exists()) {
                path.delete();
            }
        }

        if (customerobj.getCustomerAdharback() != null) {
            File path = new File(serverurl + customerobj.getCustomerAdharback());

            if (path.exists()) {
                path.delete();
            }
        }

        customerrepository.deleteById(id);
        return "User deleted successfully";
    }

    public CustomerDTO customerlogin(String username, String password) {
        CustomerRegister validobj = customerrepository.findByUsernameAndPassword(username, password);

        if (validobj != null) {

            CustomerDTO obj = new CustomerDTO();
            obj.setCid(validobj.getCid());
            obj.setUsername(validobj.getUsername());
            obj.setPassword(validobj.getPassword());

            return obj;
        }
        return null;
    }
}
