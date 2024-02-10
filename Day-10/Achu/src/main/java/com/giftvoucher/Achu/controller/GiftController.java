package com.giftvoucher.Achu.controller;

import static com.giftvoucher.Achu.utils.MyConstant.ADDGIFT;
import static com.giftvoucher.Achu.utils.MyConstant.ADMIN;
import static com.giftvoucher.Achu.utils.MyConstant.DELETEGIFT;
import static com.giftvoucher.Achu.utils.MyConstant.EDITGIFT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftvoucher.Achu.Repository.GiftRepository;
import com.giftvoucher.Achu.Repository.ThemeRepository;
import com.giftvoucher.Achu.dto.request.GiftRequest;
import com.giftvoucher.Achu.dto.response.GiftResponse;
import com.giftvoucher.Achu.models.Gift;
import com.giftvoucher.Achu.models.Theme;
import com.giftvoucher.Achu.utils.MyConstant;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
public class GiftController {

    private final GiftRepository giftRepository;
    private final ThemeRepository themeRepository;

    @PostMapping(ADDGIFT)
    public ResponseEntity<GiftResponse> addGift(@RequestBody GiftRequest giftRequest)
    {
        try
        {
            Gift g = new Gift();
            g.setGiftName(giftRequest.getGiftName());
            g.setGiftDetails(giftRequest.getGiftDetails());
            g.setGiftPrice(giftRequest.getGiftPrice());
            g.setGiftImageUrl(giftRequest.getGiftImageUrl());
            giftRepository.save(g);
            return ResponseEntity.ok(new GiftResponse("Gift Added Successfully"));
        }
        catch(Exception e) 
        {
            return new ResponseEntity<>(new GiftResponse(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(EDITGIFT)
    public ResponseEntity<GiftResponse> editGift(@RequestBody GiftRequest giftRequest,@PathVariable String id)
    {
        try
        {
            Gift g = giftRepository.findById(id)
            .orElseThrow(()->new EntityNotFoundException("No such gift exists"));

            g.setGiftName(giftRequest.getGiftName());
            g.setGiftDetails(giftRequest.getGiftDetails());
            g.setGiftPrice(giftRequest.getGiftPrice());
            g.setGiftImageUrl(giftRequest.getGiftImageUrl());

            giftRepository.save(g);

            return ResponseEntity.ok(new GiftResponse("Gift Updated Successfully"));
        }
        catch(Exception e) {
            return new ResponseEntity<>(new GiftResponse("Went wrong"),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(DELETEGIFT)
    public ResponseEntity<GiftResponse> deleteGift(@PathVariable String id)

    {
        try
        {

            giftRepository.deleteById(id);
            
            return ResponseEntity.ok(new GiftResponse("Gift Deleted Successfully"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new GiftResponse(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
        }

    }


    @GetMapping(MyConstant.GETGIFT)
    public ResponseEntity<List<Gift>> getGift()
    {
        try
        {
           List<Gift> res = giftRepository.findAll();
           return ResponseEntity.ok(res);
            
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}