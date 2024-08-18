package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private int id;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private int year;
    @NotBlank
    private String condition;
    @NotBlank
    private Double price;

}
