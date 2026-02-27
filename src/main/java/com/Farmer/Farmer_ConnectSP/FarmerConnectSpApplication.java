package com.Farmer.Farmer_ConnectSP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class FarmerConnectSpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerConnectSpApplication.class, args);
	}

}
