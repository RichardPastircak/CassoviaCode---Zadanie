package com.example.cassoviacodezadanie.products;

import com.example.cassoviacodezadanie.customer.Customer;
import com.example.cassoviacodezadanie.orders.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer productOwner;

    @JsonIgnore
    @ManyToMany(mappedBy = "productsInOrder")
    private Set<Order> orders = new HashSet<>();

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }


    //Getters & Setters


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(Customer author) {
        this.productOwner = author;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    //    To String


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productOwner=" + productOwner +
                ", orders=" + orders +
                '}';
    }
}
