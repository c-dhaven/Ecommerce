package com.project.Ecommerce;

import com.project.Ecommerce.Entity.Customer;
import com.project.Ecommerce.Entity.Role;
import com.project.Ecommerce.Repository.CustomerRepository;
import com.project.Ecommerce.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class EcommerceApplicationTests {


	@Autowired
	CustomerService customerService;
	@MockBean
	CustomerRepository customerRepository;


	@Test
	void workingTest(){
		when(customerRepository.findAll()).thenReturn(Stream.of(new Customer(1,"A","A",21,Role.USER)).collect(Collectors.toList()));
		assertEquals(1,customerService.getCustomer().size());
	}

	@Test
	void registerCustomer(){
		when(customerRepository.save(new Customer())).thenReturn(null);
		assertEquals("Customer registered successfully", customerService.registerCustomer(new Customer(1,"A","A",21,Role.USER)));
	}




}
