package com.example.Spring_Security_May25.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/v1")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("greet")
    public String greet(){
        return "hi this Admin dashboard";
    }
}
