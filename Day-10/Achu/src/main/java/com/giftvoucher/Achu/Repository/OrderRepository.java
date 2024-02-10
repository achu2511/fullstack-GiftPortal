package com.giftvoucher.Achu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giftvoucher.Achu.models.Orders;

public interface OrderRepository extends JpaRepository<Orders,String>{

    @Query("select u.orderId from Orders u where u.user.id=:userId")
    String getOrderByUserId(@Param("userId") String userId);

}
