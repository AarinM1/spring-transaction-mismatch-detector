package com.example.my_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_project.service.MismatchDetectionService;

@RestController
@RequestMapping("/api/mismatch")    
public class MismatchController {
    @Autowired
    private MismatchDetectionService mismatchDetectoinService;

    @PostMapping("/run")
    public ResponseEntity<String> runMismatchCheck(){
        mismatchDetectoinService.compareDatabases();
        return ResponseEntity.ok("Mismatch check started.");

    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Getting status.");
    }


}
