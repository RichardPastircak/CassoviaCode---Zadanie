package com.example.cassoviacodezadanie.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.login = ?1")
    Optional<Customer> findCustomerByLogin(String login);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Customer  WHERE login = ?1")
//    void deleteCustomerByLogin(String login);
}
