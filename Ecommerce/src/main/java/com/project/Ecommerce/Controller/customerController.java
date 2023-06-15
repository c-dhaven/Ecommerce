package com.project.Ecommerce.Controller;

import com.project.Ecommerce.DTO.LoginEntity;
import com.project.Ecommerce.Entity.Customer;
import com.project.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class customerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public String registerCustomer(@RequestBody Customer customer){
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/login")
    public String loginCustomer(@RequestBody LoginEntity loginEntity){
        return customerService.authenticateCustomer(loginEntity);
    }




}
