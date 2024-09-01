package com.example.carshopwithspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represents an order in the system.
 * <p>
 * The {@code Order} class models an order with details including the order ID, associated car, client, status, and date.
 * Lombok annotations are used to automatically generate boilerplate code such as getters, setters, constructors, and
 * methods like {@code toString()}, {@code equals()}, and {@code hashCode()}.
 * </p>
 *
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer car_id;
    @Column(nullable = false)
    private Integer client_id;
    @Column(nullable = false)
    private String status;
    @CreatedBy
    @Column(updatable = false)
    private String created_by;
    @CreatedDate
    @Column(updatable = false)
    private Date created_at;
    @LastModifiedBy
    private String updated_by;
    @LastModifiedDate
    private Date updated_at;

}
