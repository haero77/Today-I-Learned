package com.myshop.catalog.command.domain.product;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, ProductId> {
  void save(Product product);

  Optional<Product> findById(ProductId id);

  void flush();
}

