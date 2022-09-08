package com.example.cassoviacodezadanie.products;

import com.example.cassoviacodezadanie.customer.Customer;
import com.example.cassoviacodezadanie.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Long customerId, Product product) {
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if(productOptional.isPresent()){
            throw new IllegalStateException("Product with name " + product.getName() + " already exist, try different one.");
        }
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("Customer with id " + customerId + " doesn't exist.");
        }

        product.setProductOwner(customerOptional.get());
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long customerId, Long productId, String name, String description, Double price) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new IllegalArgumentException("Product with id " + productId + " doesn't exist.");
        }

        Product updatedProduct = product.get();
        if (!Objects.equals(updatedProduct.getProductOwner().getId(), customerId)){
            throw new IllegalArgumentException("Customer with id " + customerId + " is not owner of the product " + name + " with id " +
                    productId + " so he can't modify it.");
        }

        if (name != null && !name.isEmpty() && !Objects.equals(name, updatedProduct.getName())){
            updatedProduct.setName(name);
        }
        if(description != null && !description.isEmpty() && !Objects.equals(description, updatedProduct.getDescription())){
            updatedProduct.setDescription(description);
        }
        if(price != null && price >= 0D && price != updatedProduct.getPrice()){
            updatedProduct.setPrice(price);
        }
    }
}
