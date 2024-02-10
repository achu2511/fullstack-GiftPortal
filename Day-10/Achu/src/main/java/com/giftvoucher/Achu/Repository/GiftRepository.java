package com.giftvoucher.Achu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvoucher.Achu.models.Gift;



public interface GiftRepository extends JpaRepository<Gift,String> {
    Optional<Gift> findByGiftId(String giftId);
}
