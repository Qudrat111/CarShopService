package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a car in the system.
 * <p>
 * The {@code Car} class models a car entity with fields for car ID, make, model, year, condition, and price.
 * Lombok annotations are used to automatically generate boilerplate code such as getters, setters, constructors, and
 * methods like {@code toString()}, {@code equals()}, and {@code hashCode()}.
 * </p>
 *
 * @see lombok.Data
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    /**
     * The unique identifier for the car.
     * <p>
     * Initialized to {@code 0} by default.
     * </p>
     */
    private Integer id = 0;

    /**
     * The make of the car.
     * <p>
     * Represents the manufacturer of the car, such as "Toyota", "Ford", etc.
     * </p>
     */
    private String make;

    /**
     * The model of the car.
     * <p>
     * Represents the specific model of the car, such as "Corolla", "Mustang", etc.
     * </p>
     */
    private String model;

    /**
     * The year of manufacture of the car.
     * <p>
     * Represents the year in which the car was manufactured.
     * </p>
     */
    private Integer year;

    /**
     * The condition of the car.
     * <p>
     * Represents the state of the car, such as "New", "Used", etc.
     * </p>
     */
    private String condition;

    /**
     * The price of the car.
     * <p>
     * Represents the monetary value of the car.
     * </p>
     */
    private Double price;

    public Car(String make, String model, Integer year, String condition, Double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.condition = condition;
        this.price = price;
    }
}
