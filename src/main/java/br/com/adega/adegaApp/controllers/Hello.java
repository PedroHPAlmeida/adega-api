package br.com.adega.adegaApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/hello")
public class Hello {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String hello(){
        return "Hello, World 2!";
    }
}
