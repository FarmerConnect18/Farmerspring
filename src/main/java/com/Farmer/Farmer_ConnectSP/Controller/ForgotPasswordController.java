/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.DTOS.EmailDTO;
import com.Farmer.Farmer_ConnectSP.Services.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
//@CrossOrigin("*")
public class ForgotPasswordController {

    @Autowired
    private ForgotPassword forgotservice;

    @PostMapping("/verify-email")
    public ResponseEntity<EmailDTO> emailverify(@RequestBody EmailDTO emaildto) {
        System.out.print("API hit");
        EmailDTO obj = forgotservice.findemail(emaildto);

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(obj);

    }

    @PutMapping("/update-password/")
    public ResponseEntity<EmailDTO> UpdatePassword(@RequestBody EmailDTO emaildto) {
        EmailDTO mess = forgotservice.updatepassword(emaildto);

        if (mess == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mess);

    }
}
