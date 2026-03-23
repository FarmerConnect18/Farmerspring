/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.CustomerDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Repository.CustomerRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerrepository;
    @Autowired
    private PasswordEncoder securityobj;
    @Autowired
    private CloudinaryService cloudservice;

    private final String folderName = "Farmer-Connet/Customer";
//    private final String serverurl = "http://localhost:8080";

    public CustomerRegister addcustomer(CustomerDTO customerdto) {
//        File folder = new File(folderpath);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }

        CustomerRegister customerobj = new CustomerRegister();

        customerobj.setAddress(customerdto.getAddress());
        customerobj.setCity(customerdto.getCity());
        customerobj.setDatetime(customerdto.getDatetime());
        customerobj.setEmail(customerdto.getEmail());
        customerobj.setPassword(securityobj.encode(customerdto.getPassword()));
        customerobj.setPhoneno(customerdto.getPhoneno());
        customerobj.setState(customerdto.getState());
        customerobj.setStatus(0);
        customerobj.setUsername(customerdto.getUsername());

//        String frontimg = imagesave(customerdto.getCustomerAdhar());
//        String backimg = imagesave(customerdto.getCustomerAdharback());
        Map imagedata = cloudservice.uploadimage(customerdto.getCustomerAdhar(), folderName);
        customerobj.setCustomerAdhar(imagedata.get("url").toString());

        imagedata = cloudservice.uploadimage(customerdto.getCustomerAdharback(), folderName);
        customerobj.setCustomerAdharback(imagedata.get("url").toString());

        customerrepository.save(customerobj);
        return customerobj;
    }

//    private String imagesave(MultipartFile file) {
//        try {
//            String image = file.getOriginalFilename();
//            String imgextension = image.substring(image.lastIndexOf("."));
//            String imagename = System.currentTimeMillis() + imgextension;
//
//            Path path = Paths.get(folderpath, imagename);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//            return imagename;
//        } catch (IOException e) {
//
//            e.getMessage();
//        }
//        return null;
//    }
    public List<CustomerRegister> getcustomerlist() {
        List<CustomerRegister> customerlist = customerrepository.findAll();

//        customerlist.forEach(customer -> {
//
//            customer.setCustomerAdhar(
//                    serverurl + "/Customer-images/" + customer.getCustomerAdhar());
//
//            customer.setCustomerAdharback(
//                    serverurl + "/Customer-images/" + customer.getCustomerAdharback());
//        });
        return customerlist;
    }

    public CustomerRegister getcustomerbyid(Integer id) {
        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }

        CustomerRegister customerobj = customerop.get();

//        customerobj.setCustomerAdhar(serverurl + "/Customer-images/" + customerobj.getCustomerAdhar());
//        customerobj.setCustomerAdharback(serverurl + "/Customer-images/" + customerobj.getCustomerAdharback());
//        customerobj.setCustomerImg(serverurl + "/Customer-images/" + customerobj.getCustomerImg());
        return customerobj;
    }

    public CustomerRegister updatecustomer(Integer id, CustomerDTO customerdto) {
        Optional<CustomerRegister> customerop = customerrepository.findById(id);

        if (customerop.isEmpty()) {
            return null;
        }

        CustomerRegister customerobj = customerop.get();

        customerobj.setAddress(customerdto.getAddress());
        customerobj.setCity(customerdto.getCity());
        customerobj.setPassword(customerdto.getPassword());
        customerobj.setPhoneno(customerdto.getPhoneno());
        customerobj.setState(customerdto.getState());
        customerobj.setStatus(1);
        customerobj.setEmail(customerdto.getEmail());
        customerobj.setUsername(customerdto.getUsername());

//        String image = imagesave(customerdto.getCustomerAdhar());
//        customerobj.setCustomerAdhar(image);
//        image = imagesave(customerdto.getCustomerAdharback());
//        customerobj.setCustomerAdharback(image);
        Map imagedata = cloudservice.uploadimage(customerdto.getCustomerImg(), folderName);
        customerobj.setCustomerImg(imagedata.get("url").toString());
        customerrepository.save(customerobj);
        return customerobj;
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

//        if (customerobj.getCustomerAdhar() != null) {
//            File path = new File(serverurl + customerobj.getCustomerAdhar());
//
//            if (path.exists()) {
//                path.delete();
//            }
//        }
//
//        if (customerobj.getCustomerAdharback() != null) {
//            File path = new File(serverurl + customerobj.getCustomerAdharback());
//
//            if (path.exists()) {
//                path.delete();
//            }
//        }
        customerrepository.deleteById(id);
        return "User deleted successfully";
    }

    public CustomerDTO customerlogin(String username, String rawpassword) {

//        CustomerRegister customerobj = customerrepository.findByUsernameAndPassword(username, password);
        CustomerRegister customerobj = customerrepository.findByUsernameIgnoreCase(username);

        if (customerobj == null) {
            return null;
        }

        if (!securityobj.matches(rawpassword, customerobj.getPassword())) {
//            new RuntimeException("Customer Password not matched.......");
            return null;

        }

        CustomerDTO customerdto = new CustomerDTO();

        customerdto.setUsername(customerobj.getUsername());
        customerdto.setCid(customerobj.getCid());
        customerdto.setStatus(customerobj.getStatus());

        return customerdto;
    }
}
