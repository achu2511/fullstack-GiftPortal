package com.giftvoucher.Achu.controller;

import static com.giftvoucher.Achu.utils.MyConstant.USER;
import static com.giftvoucher.Achu.utils.MyConstant.USERLIST;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.giftvoucher.Achu.utils.MyConstant;

import jakarta.persistence.EntityNotFoundException;

import com.giftvoucher.Achu.Repository.OrderRepository;
import com.giftvoucher.Achu.Repository.PaymentRepository;
import com.giftvoucher.Achu.Repository.UserRepository;
import com.giftvoucher.Achu.dto.request.ThemeRequest;
import com.giftvoucher.Achu.dto.request.userdetailsupRequest;
import com.giftvoucher.Achu.dto.response.BasicResponse;
import com.giftvoucher.Achu.dto.response.ThemeResponse;
import com.giftvoucher.Achu.dto.response.UserResponse;
import com.giftvoucher.Achu.dto.response.userDetResponse;
import com.giftvoucher.Achu.models.Theme;
import com.giftvoucher.Achu.models.User;
import com.giftvoucher.Achu.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4000") 
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userser;
    @Autowired
    private final UserRepository userepo;

    @Autowired
    private OrderRepository ord;

    @Autowired
    private PaymentRepository prep;
    @GetMapping(USERLIST)
    public ResponseEntity <BasicResponse<UserResponse>> create(){
        BasicResponse<UserResponse> response=new BasicResponse<>();
            try{
                response=userser.getAllUser();
                return new ResponseEntity<>(response,HttpStatus.OK);
                // entityClassName savedItem=repo.save(item);
            }catch(Exception e){
                response.setMessage("Something went wrong");
                return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
            }
    }

   @PutMapping(MyConstant.UPDATEUSER)
    public ResponseEntity<userDetResponse> editTheme(@PathVariable String userId,@RequestBody userdetailsupRequest ureq)
    {
        try
        {
            User t = userepo.findById(userId)
            .orElseThrow(()->new EntityNotFoundException("No such gift exists"));

            t.setUsername(ureq.getUsername());
            t.setEmail(ureq.getEmail());
            t.setPassword(ureq.getPassword());

            userepo.save(t);

            return ResponseEntity.ok(new userDetResponse("User Details Updated Successfully"));
        }
        catch(Exception e) {
            return new ResponseEntity<>(new userDetResponse("Went wrong"),HttpStatus.EXPECTATION_FAILED);
        }
    }

     @DeleteMapping(MyConstant.DELUSER)
    public ResponseEntity<String> deleteRecharge(@PathVariable String userId) {
        try {
            String pid = prep.getPaymentByUserId(userId);
            if(pid!=null)
            {
                System.out.println(pid);
                prep.deleteById(pid);
                String oid = ord.getOrderByUserId(userId);
                if(oid!=null)
                {
                    System.out.println(oid);
                    ord.deleteById(oid);
                }
            }
           
            
            userepo.deleteById(userId);

            return ResponseEntity.status(HttpStatus.OK).body("Your Account has been deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

    }
    


}
