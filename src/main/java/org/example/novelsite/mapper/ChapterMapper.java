package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Chapter;
import java.util.List;

@Mapper
public interface ChapterMapper {

    Chapter findById(Long id);

    List<Chapter> findByNovelId(Long novelId);

    Chapter findByNovelIdAndOrder(@Param("novelId") Long novelId, @Param("chapterOrder") Integer chapterOrder);

    Chapter findPrevChapter(@Param("novelId") Long novelId, @Param("chapterOrder") Integer currentOrder);

    Chapter findNextChapter(@Param("novelId") Long novelId, @Param("chapterOrder") Integer currentOrder);

    int getMaxOrder(Long novelId);

    int insert(Chapter chapter);

    int update(Chapter chapter);

    int deleteById(Long id);

    int deleteByNovelId(Long novelId);
}
