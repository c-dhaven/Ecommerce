package com.project.Ecommerce.Controller;

import com.project.Ecommerce.Config.JWTService;
import com.project.Ecommerce.Entity.Customer;
import com.project.Ecommerce.Entity.Product;
import com.project.Ecommerce.Repository.CartRepository;
import com.project.Ecommerce.Repository.CustomerRepository;
import com.project.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    JWTService jwtService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/viewcustomer")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewcustomer(){
            List<Customer> customers = customerRepository.findAll();
            return customers.toString();
    }
    @GetMapping("/viewproduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewproduct(){
        List<Product> products = productRepository.findAll();
        return products.toString();
    }

    @PostMapping("/editcustomerdetails")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return "Customer details updated successfully.";
    }

    @PostMapping("/editproductdetails")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editProduct(@RequestBody Product product){
        productRepository.save(product);
        return "Product details updated successfully.";
    }

}
