package org.example.novelsite.service;

import org.example.novelsite.entity.Category;
import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    List<Category> findAll();

    boolean save(Category category);

    boolean deleteById(Long id);
}
