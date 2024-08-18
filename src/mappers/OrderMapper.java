package mappers;

import dto.OrderDto;
import model.Order;

public class OrderMapper {

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCar(order.getCar());
        orderDto.setClient(order.getClient());
        orderDto.setStatus(order.getStatus());
        orderDto.setDate(order.getDate());
        return orderDto;
    }

    public Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderId(orderDto.getOrderId());
        order.setCar(orderDto.getCar());
        order.setClient(orderDto.getClient());
        order.setStatus(orderDto.getStatus());
        order.setDate(orderDto.getDate());
        return order;
    }

}
