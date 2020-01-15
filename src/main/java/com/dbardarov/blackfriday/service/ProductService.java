package com.dbardarov.blackfriday.service;

import com.dbardarov.blackfriday.dao.entity.Product;
import com.dbardarov.blackfriday.dao.repository.ProductRepository;
import com.dbardarov.blackfriday.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public List<Product> getAll() {
    return this.productRepository.findAll();
  }

  @Transactional
  public Product create(String name, String description, double price, double minPrice, int quantity) {
    Product product = new Product();

    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setMinPrice(minPrice);
    product.setQuantity(quantity);

    return this.productRepository.save(product);
  }

  @Transactional
  public boolean delete(String productId) {
    this.productRepository.deleteById(productId);
    return true;
  }

  @Transactional
  public Product update(String id, String name, String description, double price, double minPrice, int quantity) {
    Optional<Product> optionalProduct = this.productRepository.findById(id);

    if (!optionalProduct.isPresent()) {
      throw new ProductNotFoundException();
    }

    Product product = optionalProduct.get();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setMinPrice(minPrice);
    product.setQuantity(quantity);

    return this.productRepository.save(product);
  }
}
