package com.giftvoucher.Achu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.giftvoucher.Achu.Repository.CustomerRepository;
import com.giftvoucher.Achu.Repository.GiftRepository;
import com.giftvoucher.Achu.Repository.OrderRepository;
import com.giftvoucher.Achu.Repository.PaymentRepository;
import com.giftvoucher.Achu.Repository.ThemeRepository;
import com.giftvoucher.Achu.Repository.UserRepository;
import com.giftvoucher.Achu.dto.response.GiftResponse;
import com.giftvoucher.Achu.models.Gift;
import com.giftvoucher.Achu.models.Orders;
import com.giftvoucher.Achu.models.Payment;
import com.giftvoucher.Achu.models.Theme;
import com.giftvoucher.Achu.models.User;
import com.giftvoucher.Achu.service.UserService;
import com.giftvoucher.Achu.utils.MyConstant;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4000") 
@RestController
@RequestMapping(MyConstant.USERSIDE)
@RequiredArgsConstructor
public class UserSideController {
    private final UserService userser;
    @Autowired
    private PaymentRepository prep;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private CustomerRepository custrep;
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private OrderRepository ord;
    @Autowired
    private UserRepository userRepository;

   

     @PostMapping(MyConstant.PAYMENTPOST)
     
    public ResponseEntity<String> makePayment(@RequestBody String paymentId) {
        Payment pending_payment=prep.findById(paymentId).get();
        System.out.println("kjasdnoadfh;oashfdoisdhf;oiSFjoifhd");
        try {
            pending_payment.setStatus("true");
            pending_payment.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            pending_payment.setModeOfPayment("razorpay");
            return ResponseEntity.status(HttpStatus.OK).body("Payment Successfullt Made!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }


    @GetMapping(MyConstant.PAYMENTVIEW)
    public ResponseEntity<?> getPayment(@PathVariable String userId) {
        try{
            System.out.print(userId);
            String aq  = prep.getPaymentByUserId(userId);
            Optional<Payment> arr = prep.findById(aq);
            return ResponseEntity.ok(arr);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(MyConstant.VIEWORDER)
    public ResponseEntity<?> viewOrder(@PathVariable String orderId) {
        try{
            System.out.print(orderId);
            Optional<Orders> aq  = ord.findById(orderId);
            return ResponseEntity.ok(aq);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(MyConstant.GETUSERNAME)
    public ResponseEntity<?> getUserName(@PathVariable String email) {
        try{
            // System.out.print(orderId);
            String aq  = userRepository.getEmail(email);
            return ResponseEntity.ok(aq);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(MyConstant.GETPRODUCTS)
    public ResponseEntity<?> getproducts(@PathVariable String imageName) {
        try{
            // System.out.print(orderId);
            List<Gift> aq = giftRepository.getProducts(imageName);
            return ResponseEntity.ok(aq);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(MyConstant.ADDGIFTTHEME)
     public ResponseEntity<GiftResponse> addGiftTheme(Principal principal,@PathVariable String themeId,@PathVariable String giftId,@PathVariable Integer quantity)
    {
        try
        {
            Theme t = themeRepository.findById(themeId)
            .orElseThrow(()->new EntityNotFoundException("No such theme exists"));
            System.out.print(t);
            Gift g = giftRepository.findByGiftId(giftId)
            .orElseThrow(()->new EntityNotFoundException("No such gift exists"));

           

            
            Orders o = new Orders();

            String mail = principal.getName();
		    String temp=userRepository.getIdFromMail(mail);
            System.out.print(temp);
		    User user = userRepository.findById(temp)	
		    .orElseThrow(() -> new EntityNotFoundException("User with email " + mail + " not found"));
            o.setUser(user);
            System.out.println(temp);
            double tp = Double.parseDouble(t.getThemePrice());
            double gp = Double.parseDouble(g.getGiftPrice());
            double price = tp+gp;
            o.setOrder_price(price);
            o.setQuantity(quantity);
            o.setGift(g);
            o.setTheme(t);
            o.setOrder_date(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            

            ord.save(o);
            Payment payment= new Payment();
            payment.setStatus("false");
            payment.setModeOfPayment(null);
            payment.setAmoutPaid(price);
            payment.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            payment.setUser(user);
            payment.setOrder(o);
            prep.save(payment);

            return ResponseEntity.ok(new GiftResponse("Order added successfully"));
        }
        catch(Exception e) {
            return new ResponseEntity<>(new GiftResponse(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteRecharge(@PathVariable String orderId) {
        try {
            String aq  = prep.getPaymentByOrderId(orderId);
            prep.deleteById(aq);
            ord.deleteById(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("Order Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // @PutMapping(MyConstant.EDITORDER)
    // public ResponseEntity<String> editOrder(@RequestBody Orders order,@PathVariable String orderId) 
    // {
    //     try
    //     {
    //         Orders o = ord.findById(orderId)
    //         .orElseThrow(()->new EntityNotFoundException("No such order exists"));

    //         o.setQuantity(order.getQuantity());
    //         return ResponseEntity.status(HttpStatus.OK).body("Order Updated Successfully");
    //     }
    //     catch(Exception e)
    //     {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    //     }
    // }
}