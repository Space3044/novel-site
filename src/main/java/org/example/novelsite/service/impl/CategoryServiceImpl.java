package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Category;
import org.example.novelsite.mapper.CategoryMapper;
import org.example.novelsite.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public boolean save(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return categoryMapper.deleteById(id) > 0;
    }
}
