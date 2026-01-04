package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Bookmark;
import java.util.List;

@Mapper
public interface BookmarkMapper {

    Bookmark findById(Long id);

    List<Bookmark> findByReaderId(Long readerId);

    Bookmark findByReaderAndNovel(@Param("readerId") Long readerId, @Param("novelId") Long novelId);

    int insert(Bookmark bookmark);

    int deleteById(Long id);

    int deleteByReaderAndNovel(@Param("readerId") Long readerId, @Param("novelId") Long novelId);
}
