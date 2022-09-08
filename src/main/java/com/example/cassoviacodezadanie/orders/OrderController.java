package com.example.cassoviacodezadanie.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping
    public void createOrder(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestBody Order order){
        orderService.createOrder(customerId, productId, order);
    }

    @PutMapping(path = "/{orderId}/{productId}")
    public void addProduct(
            @PathVariable Long orderId,
            @PathVariable Long productId){
        orderService.addProduct(orderId, productId);
    }

//    @DeleteMapping(path = "/{orderId}")
//    public void deleteOrder(@PathVariable Long orderId){
//        orderService.deleteOrder(orderId);
//    }
}
