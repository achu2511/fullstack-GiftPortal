package com.giftvoucher.Achu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giftvoucher.Achu.models.Gift;
// import com.giftvoucher.Achu.models.Orders;



public interface GiftRepository extends JpaRepository<Gift,String> {
    Optional<Gift> findByGiftId(String giftId);


    @Query("select u from Gift u where u.Category=:a")
	List<Gift> getProducts(@Param("a") String imageName);
}
