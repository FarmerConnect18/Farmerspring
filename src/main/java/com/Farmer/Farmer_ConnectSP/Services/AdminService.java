/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.EmailDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Admin;
import com.Farmer.Farmer_ConnectSP.Repository.AdminRepository;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminrepo;
    @Autowired
    private PasswordEncoder securityobj;
    @Autowired
    private JavaMailSender javamailsender;

    public Admin getAdmin(String Password, String Username) {
        Admin obj = adminrepo.findByUsernameIgnoreCase(Username);

        if (obj == null) {
            return null;
        }
        if (!securityobj.matches(Password, obj.getPassword())) {
            return null;
        }
        Admin adminobj = new Admin();
        adminobj.setAid(obj.getAid());
        adminobj.setEmail(obj.getEmail());
        adminobj.setUsername(obj.getUsername());

        return adminobj;

    }

    public EmailDTO createAdmin(EmailDTO emaildto) {
        Admin adminobj = new Admin();
        adminobj.setEmail(emaildto.getEmail());
        adminobj.setUsername(emaildto.getUsername());
        adminobj.setPassword(securityobj.encode(emaildto.getPassword()));

        adminrepo.save(adminobj);
        return emaildto;

    }

    public EmailDTO adminemailverify(EmailDTO emaildto) {
        Random random = new Random(100000);
        SimpleMailMessage message = new SimpleMailMessage();

        Admin adminobj = adminrepo.findByemail(emaildto.getEmail());

        if (adminobj == null) {
            return null;
        }

        Integer opt = random.nextInt(999999);

        message.setTo(adminobj.getEmail());
        message.setSubject("OTP Verification for Your Account");
        message.setText("Dear User,\n"
                + "\n"
                + "🔐 Account Verification Required\n"
                + "\n"
                + "To complete your account verification, please use the One-Time Password (OTP) given below.\n"
                + "\n"
                + "🔑 Your OTP:" + opt + "\n"
                + "\n"
                + "⏳ This OTP is valid for a limited time.\n"
                + "⚠️ For security reasons, please do not share this OTP with anyone.\n"
                + "\n"
                + "❓ If you did not request this verification, please ignore this email or contact the administrator.\n"
                + "\n"
                + "Thank you for your cooperation.\n"
                + "\n"
                + "Best regards,\n"
                + "👨‍💼 Admin Team\n"
                + "🌾 Farmer Connect System");

        javamailsender.send(message);

        EmailDTO otpdto = new EmailDTO();

        otpdto.setEmail(adminobj.getEmail());
        otpdto.setId(adminobj.getAid());
        otpdto.setUsername(adminobj.getUsername());
        otpdto.setOpt(opt);

        return otpdto;
    }

    public EmailDTO passwordupdate(EmailDTO emaildto) {
        Optional<Admin> adminop = adminrepo.findById(emaildto.getId());
        Admin adminobj = adminop.get();

        adminobj.setPassword(securityobj.encode(emaildto.getPassword()));

        adminrepo.save(adminobj);
        emaildto.setPassword(null);
        return emaildto;

    }
}
