package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Reader;
import java.util.List;

@Mapper
public interface ReaderMapper {

    Reader findById(Long id);

    Reader findByUsername(String username);

    List<Reader> findAll();

    int insert(Reader reader);

    int update(Reader reader);

    int deleteById(Long id);

}
