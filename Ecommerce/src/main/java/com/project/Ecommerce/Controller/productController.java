package com.project.Ecommerce.Controller;

import com.project.Ecommerce.Config.JWTService;
import com.project.Ecommerce.Entity.Cart;
import com.project.Ecommerce.Entity.Customer;
import com.project.Ecommerce.Entity.Product;
import com.project.Ecommerce.Repository.CartRepository;
import com.project.Ecommerce.Repository.CustomerRepository;
import com.project.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    JWTService jwtService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/viewproduct")
    public String view(){
            List<Product> products = productRepository.findAll();
            return products.toString();
    }
    @PostMapping("/addtocart")
    public String addtoCart(@RequestBody Cart cart){
        cartRepository.save(cart);
        return "Product Successfully added to cart";
    }

    @DeleteMapping("/removecart/{id}")
    public String removeCart(@PathVariable("id") int id){
        cartRepository.deleteById(id);
        return "Product removed from cart";
    }

    @GetMapping("/viewcart")
    public String viewCart(@RequestHeader("Authorization") String token){
        Optional<Customer> customer = customerRepository.findByUsername(jwtService.extractUsername(token.substring(7)));
        String customerName = customer.get().getUsername();
        List<Cart> cart = cartRepository.findAll();
        return "Hi"+" "+customerName+","+"The products in your cart are,\n"+cart.toString();

    }

    @GetMapping("/viewbill")
    public String viewBill(@RequestHeader("Authorization") String token) {
        Optional<Customer> customer = customerRepository.findByUsername(jwtService.extractUsername(token.substring(7)));
        if (customer.isEmpty()) {
            // Handle the case when the customer is not found
            return "Customer not found";
        }

        StringBuilder bill = new StringBuilder();
        bill.append("Bill for Customer: ").append(customer.get().getUsername()).append("\n");
        bill.append("Mobile: ").append(customer.get().getMobile()).append("\n\n");
        bill.append("---------------------------------------------------");

        List<Cart> cart = cartRepository.findAll();
        if (cart.isEmpty()) {
            bill.append("No items in the cart");
        } else {
            int totalAmount = 0;
            bill.append("Item\t\t\t\t\t\t\tPrice\n");
            for (Cart item : cart) {
                bill.append(item.getProduct_name()).append("\t\t").append(item.getProductamount()).append("\n");
                totalAmount += item.getProductamount();
            }
            bill.append("---------------------------------------------------");
            bill.append("\nTotal Amount: ").append(totalAmount);
        }

        return bill.toString();
    }


}
