package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//    ArrayList<Product> products = new ArrayList<>(Arrays.asList(
//                new Product("iPhone 4","Apple",1000),
//                new Product("iPhone 5","Banana",2000),
//                new Product("iPhone 6","Orange",3000)
//        ));

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getAProduct(long id){
        return productRepository.findById(id);
    }

    public void deleteById(long id){
        productRepository.deleteById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Iterable<Product> findByBrand(String brand) { return productRepository.findByBrand(brand); }

}
