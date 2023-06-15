package com.project.Ecommerce.Service;

import com.project.Ecommerce.Config.JWTService;
import com.project.Ecommerce.DTO.LoginEntity;
import com.project.Ecommerce.Entity.Customer;
import com.project.Ecommerce.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomer{

    @Autowired
    CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public String registerCustomer(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        System.out.println(customer.getUsername());
        customerRepository.save(customer);
        return "Customer registered successfully";
    }

    public String authenticateCustomer(LoginEntity loginEntity){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginEntity.getUsername(),
                        loginEntity.getPassword()
                )
        );
        var user = customerRepository.findByUsername(loginEntity.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }

    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }



}
