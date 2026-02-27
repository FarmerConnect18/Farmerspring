/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Controller;

import com.Farmer.Farmer_ConnectSP.Entities.Contact;
import com.Farmer.Farmer_ConnectSP.Repository.ContactRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preml
 */
@RestController
@CrossOrigin("*")
public class ContactController {

    @Autowired
    private ContactRepository contactrepo;

    @PostMapping("/get-message")
    public ResponseEntity<Contact> getmessage(@ModelAttribute Contact contactobj) {
        Contact mess = contactrepo.save(contactobj);

        if (mess == null) {
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mess);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Contact>> getmessages() {
        List<Contact> list = contactrepo.findAll();

        if (list == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/deletemess/{id}")
    public ResponseEntity deletemess(@ModelAttribute Contact contactobj) {
        contactrepo.deleteById(contactobj.getId());
        return ResponseEntity.ok().body(this);
    }
}
