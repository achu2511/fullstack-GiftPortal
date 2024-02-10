package com.giftvoucher.Achu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvoucher.Achu.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,String>{

}
