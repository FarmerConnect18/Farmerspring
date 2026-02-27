package com.Farmer.Farmer_ConnectSP;

import com.Farmer.Farmer_ConnectSP.Services.Emailservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FarmerConnectSpApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private Emailservice emailservice;

//    @Test
//    void testingmail() {
//        emailservice.sendmail("tanmaychavan07018@gmail.com", "Farmer-Connect Email Testing",
//                "👋 Dear tanmayychavan,\n"
//                + "\n"
//                + " 🎉 We’re happy to inform you that your entry request for the Farmer-Connect website has been successfully approved.\n"
//                + "\n"
//                + "You can now log in to your account and start using the platform to access available features and services.\n"
//                + "\n"
//                + "If you have any questions or need assistance, feel free to contact our support team.\n"
//                + "\n"
//                + "Welcome to Farmer-Connect, and we look forward to working with you.\n"
//                + "\n"
//                + "🙏 Best regards,\n"
//                + "Farmer-Connect Team");
//    }
}
