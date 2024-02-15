package com.giftvoucher.Achu.models;

import java.util.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;


    // @Column(unique = false)
    @ManyToOne
    @JoinColumn(name = "themeId")
    private Theme theme;

    // @Column(unique = false)
    @ManyToOne
    @JoinColumn(name = "giftId")
    private Gift gift;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 

    private Double order_price;
    private Date order_date;
    private int quantity;

}
