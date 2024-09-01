package com.example.carshopwithspringboot.controller;

import com.example.carshopwithspringboot.dto.OrderDto;
import com.example.carshopwithspringboot.model.Order;
import com.example.carshopwithspringboot.service.impl.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            description = "This will add new order",
            responses = {
                    @ApiResponse(description = "Order created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad request something was wrong", responseCode = "400"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "You should give me order in request body and I will return it in json"
            )
    )
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(
            @Valid
            @RequestBody
            OrderDto orderDto,
            BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order add = orderService.add(orderDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @Operation(description = "This is for getting order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
            },
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of order which you want get"
                    )
            })
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(
            @PathVariable(name = "id") Integer order_id
    ) {
        Optional<Order> order = orderService.get(order_id);
        return new ResponseEntity<>(order.orElse(null), HttpStatus.OK);
    }

    @Operation(
            description = "This will edit exist order",
            responses = {
                    @ApiResponse(description = "Order updated successfully", responseCode = "202"),
                    @ApiResponse(description = "Bad request something was wrong", responseCode = "400"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "You should give me car in request body and I will return it in json"
            ),
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of car which you want update"
                    )
            }
    )
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrderStatus(
            @Valid
            @PathVariable(name = "id") Integer orderId,
            @RequestBody OrderDto orderDto,
            BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto.setId(orderId);
        Order update = orderService.update(orderDto);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }


    @Operation(description = "This is for cancelling order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
            },
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of order which you want cancel"
                    )
            })
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @DeleteMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> cancelOrder(Integer orderId) {
        Integer delete = orderService.delete(orderId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @Operation(description = "This is for getting all orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Access denied"),
            }
    )
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> all = orderService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
