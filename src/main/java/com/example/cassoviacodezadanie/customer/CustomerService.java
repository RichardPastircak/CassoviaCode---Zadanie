package com.example.cassoviacodezadanie.customer;

import com.example.cassoviacodezadanie.orders.Order;
import com.example.cassoviacodezadanie.orders.OrderRepository;
import com.example.cassoviacodezadanie.products.Product;
import com.example.cassoviacodezadanie.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void createNewCustomer(Customer customer){
        Optional<Customer> customerOptional = customerRepository.findCustomerByLogin(customer.getLogin());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("This login is already used, try different one.");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()){
            throw new IllegalStateException("Customer with id " + customerId + " doesn't exist. I can't delete him.");
        }
        Customer deletedCustomer = customerOptional.get();
        deleteConnections(deletedCustomer);
//        customerRepository.deleteById(customerId);
    }

    public void deleteConnections(Customer customer){
        for (Product product : customer.getProducts()) {
            for (Iterator<Order> orderIterator = product.getOrders().iterator(); orderIterator.hasNext(); ) {
                Order order = orderIterator.next();
                orderIterator.remove();
                orderRepository.delete(order);
            }
        }
    }
}
