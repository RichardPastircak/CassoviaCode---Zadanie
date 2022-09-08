package com.example.cassoviacodezadanie.orders;

import com.example.cassoviacodezadanie.customer.Customer;
import com.example.cassoviacodezadanie.customer.CustomerRepository;
import com.example.cassoviacodezadanie.products.Product;
import com.example.cassoviacodezadanie.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void createOrder(Long customerId, Long productId, Order order) {
        Optional<Order> optionalOrder = orderRepository.findOrderByName(order.getName());
        if(optionalOrder.isPresent()){
            throw new IllegalArgumentException("Order with name " + order.getName() + "already exist.");
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new IllegalArgumentException("Customer with id " + customerId + " doesn't exist.");
        }
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new IllegalArgumentException("Product with id " + productId + " doesn't exist.");
        }

        order.setOrderCreator(optionalCustomer.get());
        order.addProduct(optionalProduct.get());
        orderRepository.save(order);
    }

    @Transactional
    public void addProduct(Long orderId, Long productId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new IllegalArgumentException("Order with id " + orderId + " doesn't exist.");
        }
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new IllegalArgumentException("Product with id " + productId + " doesn't exist.");
        }

        Order updatedOrder = optionalOrder.get();
        updatedOrder.addProduct(optionalProduct.get());
    }
}
