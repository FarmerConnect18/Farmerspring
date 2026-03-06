///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.Farmer.Farmer_ConnectSP.Configurefolder;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// *
// * @author preml
// *
// */
//@Configuration
//public class AuthSecurity {
//
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> {
//                })
//                .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/farmer-login/**").permitAll()
//                .anyRequest().permitAll()
//                )
//                .formLogin(form -> form.disable())
//                .httpBasic(basic -> basic.disable())
//                ;
//
//        return http.build();
//    }
//
//}
