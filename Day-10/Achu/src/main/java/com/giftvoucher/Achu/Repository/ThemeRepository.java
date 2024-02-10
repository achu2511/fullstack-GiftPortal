package com.giftvoucher.Achu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvoucher.Achu.models.Theme;

import java.util.Optional;


public interface ThemeRepository extends JpaRepository<Theme,String>{
   
}
