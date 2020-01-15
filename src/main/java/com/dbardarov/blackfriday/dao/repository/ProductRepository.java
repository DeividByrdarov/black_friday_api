package com.dbardarov.blackfriday.dao.repository;

import com.dbardarov.blackfriday.dao.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
