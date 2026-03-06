/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Configurefolder;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author preml
 */
@Configuration
public class Farmerimageconfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        registry.addResourceHandler("/Farmer-photo/**")
                .addResourceLocations("file:E:/Project/projectimages/Farmer-photo/");

        registry.addResourceHandler("/Customer-images/**")
                .addResourceLocations("file:E:/Project/projectimages/Customer-images/");

        registry.addResourceHandler("/Product-images/**")
                .addResourceLocations("file:E:/Project/projectimages/Product-images/");

        registry.addResourceHandler("/Junction-images/**")
                .addResourceLocations("file:E:/Project/projectimages/Junction-images/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        WebMvcConfigurer.super.addCorsMappings(registry); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        registry.addMapping("/**")
                .allowedOrigins("https://farmerconnectprl.netlify.app/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);

    }

}
