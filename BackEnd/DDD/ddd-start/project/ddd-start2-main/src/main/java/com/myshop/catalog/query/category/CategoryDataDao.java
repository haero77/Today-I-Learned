package com.myshop.catalog.query.category;

import com.myshop.catalog.command.domain.category.CategoryId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CategoryDataDao extends Repository<CategoryData, CategoryId> {
  Optional<CategoryData> findById(CategoryId id);

  List<CategoryData> findAll();
}
