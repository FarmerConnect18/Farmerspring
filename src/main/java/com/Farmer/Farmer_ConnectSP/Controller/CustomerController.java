/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.CustomerDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Services.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin()
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @PostMapping("/customer-register")
    public ResponseEntity<CustomerRegister> customerregister(@ModelAttribute CustomerDTO customerdto) {
        CustomerRegister save = customerservice.addcustomer(customerdto);

        if (save == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(save);

    }

    @PostMapping("/customer-login")
    public ResponseEntity<CustomerDTO> customervalidation(@ModelAttribute CustomerDTO customerdto) {
        CustomerDTO customerobj = customerservice.customerlogin(customerdto.getUsername(), customerdto.getPassword());

        System.out.println(customerobj.getCid());

        if (customerobj != null) {
            return ResponseEntity.ok(customerobj);
        }
        return null;
    }

    @GetMapping("/get-customer")
    public ResponseEntity<List<CustomerRegister>> getcutomer() {

        List<CustomerRegister> list = customerservice.getcustomerlist();

        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    /// @param id
    /// @return
   @GetMapping("/get-customer/{id}")
    public ResponseEntity<CustomerRegister> customerbyid(@PathVariable Integer id) {
        CustomerRegister obj = customerservice.getcustomerbyid(id);

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(obj);
    }

    /**
     *
     * @param id
     * @param customerdto
     * @return
     */
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<String> updatecutomer(@PathVariable Integer id, @ModelAttribute CustomerDTO customerdto) {

        String mess = customerservice.updatecustomer(id, customerdto);

        if (mess == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mess);
    }

    @PutMapping("/update-customerstatus/{id}")
    public ResponseEntity<CustomerRegister> updatestatus(@PathVariable Integer id, @ModelAttribute CustomerDTO customerdto) {

        CustomerRegister customerobj = customerservice.unblockcustomer(id, customerdto);

        if (customerobj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerobj);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deletecustomer(@PathVariable Integer id) {

        String mess = customerservice.deletecustomer(id);
        if (mess.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mess);
    }
}
