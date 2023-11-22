package com.binar.binarfud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/homeless")
    public String home(){
        return "selamat datang di binar";
    }

    @GetMapping("/admin")
    public String admin(){
        return "ini halaman admin weh";
    }
}
