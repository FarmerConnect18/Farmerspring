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
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author preml
 */
@Service
public class Emailservice {

    @Autowired
    private JavaMailSender javamailsender;

    @Autowired
    private FarmerRepository farmerrop;

    @Autowired
    private CustomerRepository customerrepo;

    /**
     *
     * @param to
     * @param subject
     */
    public SimpleMailMessage sendmailtofarmer(@ModelAttribute FarmerDTO farmerdto) {

        Optional<FarmerRegister> farmerop = farmerrop.findById(farmerdto.getFid());

        if (farmerop.isEmpty()) {
            return null;
        }
        FarmerRegister farmerobj = farmerop.get();

        farmerobj.setStatus(1);
        farmerrop.save(farmerobj);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(farmerobj.getEmail());
        message.setSubject("");
        message.setText("👋 Dear" + farmerobj.getUsername() + "\n"
                + "\n"
                + "🎉 We are pleased to inform you that your entry request for the Farmer-Connect website has been approved.\n"
                + "\n"
                + "🔑 You can now log in and start using the platform to:\n"
                + "\n"
                + "🌱 Access farming resources\n"
                + "\n"
                + "🤝 Connect with other farmers\n"
                + "\n"
                + "📊 Manage your activities\n"
                + "\n"
                + "🚜 Login to your account and get started.\n"
                + "\n"
                + "❓ If you need any help or support, feel free to reach out to us.\n"
                + "\n"
                + "🙏 Thank you for joining Farmer-Connect. We’re glad to have you with us.\n"
                + "\n"
                + "Best regards,\n"
                + "Farmer-Connect Team");

        javamailsender.send(message);
        return message;
    }

    public SimpleMailMessage sendmailtocustomer(@ModelAttribute CustomerDTO customerdto) {

        Optional<CustomerRegister> customerrop = customerrepo.findById(customerdto.getCid());

        if (customerrop.isEmpty()) {
            return null;
        }
        CustomerRegister customerobj = customerrop.get();

        customerobj.setStatus(1);
        customerrepo.save(customerobj);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customerobj.getEmail());
        message.setSubject("");
        message.setText("👋 Dear" + customerobj.getUsername() + "\n"
                + "\n"
                + "🎉 We are pleased to inform you that your entry request for the Farmer-Connect website has been approved.\n"
                + "\n"
                + "🔑 You can now log in and start using the platform to:\n"
                + "\n"
                + "🌱 Access farming resources\n"
                + "\n"
                + "🤝 Connect with other farmers\n"
                + "\n"
                + "📊 Manage your activities\n"
                + "\n"
                + "🚜 Login to your account and get started.\n"
                + "\n"
                + "❓ If you need any help or support, feel free to reach out to us.\n"
                + "\n"
                + "🙏 Thank you for joining Farmer-Connect. We’re glad to have you with us.\n"
                + "\n"
                + "Best regards,\n"
                + "Farmer-Connect Team");

        javamailsender.send(message);
        return message;
    }

    public SimpleMailMessage blockfarmer(FarmerDTO farmerdto) {
        FarmerRegister farmerobj = farmerrop.findByemail(farmerdto.getEmail());

        farmerobj.setStatus(0);
        farmerrop.save(farmerobj);
        SimpleMailMessage message = blockemailfarmer(farmerdto);
        return message;
    }

    public SimpleMailMessage blockcustomer(CustomerDTO customerdto) {
        CustomerRegister customerobj = customerrepo.findByemail(customerdto.getEmail());

        customerobj.setStatus(0);
        customerrepo.save(customerobj);
        SimpleMailMessage message = blockemailcustomer(customerdto);
        return message;
    }

    public SimpleMailMessage blockemailfarmer(FarmerDTO farmerdto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(farmerdto.getEmail());
        message.setSubject("Important Notice: Your Farmer Connect Account Has Been Temporarily Blocked");
        message.setText("Dear" + farmerdto.getUsername() + "\n"
                + "\n"
                + "We would like to inform you that your Farmer Connect account has been temporarily blocked due to a violation of our platform policies / suspicious activity / incomplete verification (please specify the appropriate reason).\n"
                + "\n"
                + "This action has been taken to ensure the safety, transparency, and trust of all users on our platform.\n"
                + "\n"
                + "If you believe this action was taken in error or would like to understand the reason in more detail, we kindly request you to contact the Farmer Connect Admin Team at:\n"
                + "\n"
                + "📧 [support@farmerconnect.com](mailto:support@farmerconnect.com)\n"
                + "📞 +91-XXXXXXXXXX\n"
                + "\n"
                + "Please mention your registered mobile number or email ID while contacting us for faster assistance.\n"
                + "\n"
                + "We are committed to resolving this matter as soon as possible.\n"
                + "\n"
                + "Thank you for your cooperation.\n"
                + "\n"
                + "Best Regards,\n"
                + "Admin Team\n"
                + "Farmer Connect");

        javamailsender.send(message);
        return message;
    }

    public SimpleMailMessage blockemailcustomer(CustomerDTO customerdto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customerdto.getEmail());
        message.setSubject("Important Notice: Your Farmer Connect Account Has Been Temporarily Blocked");
        message.setText("Dear" + customerdto.getUsername() + "\n"
                + "\n"
                + "We would like to inform you that your Farmer Connect account has been temporarily blocked due to a violation of our platform policies / suspicious activity / incomplete verification (please specify the appropriate reason).\n"
                + "\n"
                + "This action has been taken to ensure the safety, transparency, and trust of all users on our platform.\n"
                + "\n"
                + "If you believe this action was taken in error or would like to understand the reason in more detail, we kindly request you to contact the Farmer Connect Admin Team at:\n"
                + "\n"
                + "📧 [support@farmerconnect.com](mailto:support@farmerconnect.com)\n"
                + "📞 +91-XXXXXXXXXX\n"
                + "\n"
                + "Please mention your registered mobile number or email ID while contacting us for faster assistance.\n"
                + "\n"
                + "We are committed to resolving this matter as soon as possible.\n"
                + "\n"
                + "Thank you for your cooperation.\n"
                + "\n"
                + "Best Regards,\n"
                + "Admin Team\n"
                + "Farmer Connect");

        javamailsender.send(message);
        return message;
    }
}
