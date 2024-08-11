package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.LocalDateTime;

/**
 * Represents an order in the system.
 * <p>
 * The {@code Order} class models an order with details including the order ID, associated car, client, status, and date.
 * Lombok annotations are used to automatically generate boilerplate code such as getters, setters, constructors, and
 * methods like {@code toString()}, {@code equals()}, and {@code hashCode()}.
 * </p>
 *
 * @see lombok.Data
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 * @see model.Car
 * @see model.User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * The unique identifier for the order.
     * <p>
     * This field is intended to uniquely identify an order within the system.
     * </p>
     */
    private int id;

    /**
     * The order ID.
     * <p>
     * Initialized to {@code 0} by default. This field represents the order's identifier and can be used for tracking purposes.
     * </p>
     */
    private Integer orderId = 0;

    /**
     * The car associated with the order.
     * <p>
     * Represents the {@link Car} object that is being ordered.
     * </p>
     */
    private Car car;

    /**
     * The client who placed the order.
     * <p>
     * Represents the {@link User} object who placed the order.
     * </p>
     */
    private User client;

    /**
     * The status of the order.
     * <p>
     * Represents the current status of the order, such as "Pending", "Shipped", "Delivered", etc.
     * </p>
     */
    private String status;

    /**
     * The date and time when the order was placed.
     * <p>
     * Represents the date and time of the order using {@link LocalDateTime}.
     * </p>
     */
    private Date date;

    public Order(Date now, String pending, User client, Car car) {
        this.date = now;
        this.status = pending;
        this.client = client;
        this.car = car;
    }
}
