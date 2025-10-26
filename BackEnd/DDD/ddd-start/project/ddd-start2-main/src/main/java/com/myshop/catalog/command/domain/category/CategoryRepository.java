package com.myshop.catalog.command.domain.category;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends Repository<Category, CategoryId> {
  Optional<Category> findById(CategoryId id);

  List<Category> findAll();
}
