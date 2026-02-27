/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.EmailDTO;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Repository.CustomerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import java.util.Random;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class ForgotPassword {

    @Autowired
    private FarmerRepository farmerrepo;
    @Autowired
    private CustomerRepository customerrepo;
    @Autowired
    JavaMailSender javamilsender;

    Random random = new Random(100000);

    public EmailDTO findemail(EmailDTO emaildto) {

        System.out.println("hit here it");

        if (emaildto.getRole().equals("farmer")) {
           
            FarmerRegister farmobj = farmerrepo.findByemail(emaildto.getEmail());

            if (farmobj == null) {
                return null;
            }

            emaildto = sendemail(emaildto);
            return emaildto;

        }
        
        else if (emaildto.getRole().equals("customer")) {

            CustomerRegister customonj = customerrepo.findByemail(emaildto.getEmail());

            if (customonj == null) {
                return null;
            }

            emaildto = sendemail(emaildto);
            return emaildto;
        }
        return null;
    }

    public String updatepassword(EmailDTO emaildto) {

        if (emaildto.getRole().equals("customer")) {
            CustomerRegister customobj = customerrepo.findByemail(emaildto.getEmail());

            if(customobj==null)
            {
                return null;
            }
            customobj.setPassword(emaildto.getPassword());
            customerrepo.save(customobj);

            return "updated successufully";
        }

        if (emaildto.getRole().equals("farmer")) {
            FarmerRegister farmobj = farmerrepo.findByemail(emaildto.getEmail());

            if(farmobj==null)
            {
                return null;
            }
            farmobj.setPassword(emaildto.getPassword());
            farmerrepo.save(farmobj);

            return "updated successufully";
        }
        return null;

    }

    public EmailDTO sendemail(EmailDTO emaildto) {

        SimpleMailMessage message = new SimpleMailMessage();

        Integer opt = random.nextInt(999999);
        emaildto.setOpt(opt);
        message.setTo(emaildto.getEmail());
        message.setSubject("OTP for Password Reset – Farmer Connect");
        message.setText("Dear User,\n"
                + "\n"
                + "We received a request to reset your password for your Farmer Connect account.\n"
                + "\n"
                + "To proceed with changing your password, please use the One-Time Password (OTP) provided below:\n"
                + "\n"
                + opt + "\n"
                + "\n"
                + "This OTP is valid for the next 10 minutes. Please do not share this code with anyone for security reasons.\n"
                + "\n"
                + "If you did not request a password reset, please ignore this email or contact our support team immediately.\n"
                + "\n"
                + "Thank you for using Farmer Connect.\n"
                + "\n"
                + "Best Regards,\n"
                + "Farmer Connect Team\n"
                + "Support Email: [support@farmerconnect.com](mailto:support@farmerconnect.com)");

        javamilsender.send(message);
        return emaildto;
    }
}
