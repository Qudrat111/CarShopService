package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Car;
import model.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private int orderId;
    @NotBlank
    private Car car;
    @NotBlank
    private User client;
    @NotBlank
    private String status;
    private Date date;
}
