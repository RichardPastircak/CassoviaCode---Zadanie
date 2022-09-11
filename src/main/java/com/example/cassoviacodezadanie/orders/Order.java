package com.example.cassoviacodezadanie.orders;

import com.example.cassoviacodezadanie.customer.Customer;
import com.example.cassoviacodezadanie.products.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR")
    private String name;

    @Column(name = "price_modifier", columnDefinition = "DECIMAL")
    private Double priceModifier;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer orderCreator;

    @ManyToMany
    @JoinTable(
            name = "products_in_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> productsInOrder = new HashSet<>();


    public Order(String name, String description, Double priceModifier) {
        this.name = name;
        this.description = description;
        this.priceModifier = priceModifier;
    }

    public Order() {
    }

    //Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(Double priceModifier) {
        this.priceModifier = priceModifier;
    }

    public Customer getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(Customer orderCreator) {
        this.orderCreator = orderCreator;
    }

    public Set<Product> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(Set<Product> products) {
        this.productsInOrder = products;
    }
    //To String
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceModifier=" + priceModifier +
                ", description='" + description + '\'' +
                ", orderCreator=" + orderCreator +
                ", productsInOrder=" + productsInOrder +
                '}';
    }

    //Own Methods
    public void addProduct(Product product) {
        productsInOrder.add(product);
    }
}
