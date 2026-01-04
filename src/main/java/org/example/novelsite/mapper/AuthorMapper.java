package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Author;
import java.util.List;

@Mapper
public interface AuthorMapper {

    Author findById(Long id);

    Author findByUsername(String username);

    List<Author> findAll();

    int insert(Author author);

    int update(Author author);

    int deleteById(Long id);

}
