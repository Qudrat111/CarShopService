package com.example.carshopwithspringboot.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private int orderId;
    @NotBlank
    private Integer car_id;
    @NotBlank
    private Integer client_id;
    @NotBlank
    private String status;
    private Date date;
}
