package com.example.cassoviacodezadanie.customer;

import com.example.cassoviacodezadanie.orders.Order;
import com.example.cassoviacodezadanie.products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "login", nullable = false, unique = true, columnDefinition = "VARCHAR")
    private String login;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "productOwner", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "orderCreator", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Order> orders = new HashSet<>();

    public Customer(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Customer() {
    }

//    Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    //To String

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
