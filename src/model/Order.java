package model;

import java.time.LocalDateTime;

public class Order {
    private Integer orderId = 0;
    private Car car;
    private User client;
    private String status;
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Order(Car car, User client, String status, LocalDateTime date) {
        orderId++;
        this.car = car;
        this.client = client;
        this.status = status;
        this.date = date;
    }
    public Order (){

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", car=" + car +
                ", client=" + client +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
