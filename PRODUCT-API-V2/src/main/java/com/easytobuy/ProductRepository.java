package com.easytobuy;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
@JaversSpringDataAuditable
public interface ProductRepository extends MongoRepository<Product, String> {
List<Product> findByCategoryId(String categoryId);
List<Product> findByProductId(String productId);
}
