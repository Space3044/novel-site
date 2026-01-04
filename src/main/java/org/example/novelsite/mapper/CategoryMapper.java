package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.novelsite.entity.Category;
import java.util.List;

@Mapper
public interface CategoryMapper {

    Category findById(Long id);

    List<Category> findAll();

    int insert(Category category);

    int update(Category category);

    int deleteById(Long id);
}
