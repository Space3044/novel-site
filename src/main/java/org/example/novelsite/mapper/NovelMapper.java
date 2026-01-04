package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Novel;
import java.util.List;

@Mapper
public interface NovelMapper {

    Novel findById(Long id);

    List<Novel> findAll();

    List<Novel> findByAuthorId(Long authorId);

    List<Novel> findByCategoryId(Long categoryId);

    List<Novel> findByStatus(Integer status);

    List<Novel> search(@Param("keyword") String keyword);

    List<Novel> findTopByViewCount(@Param("limit") int limit);

    List<Novel> findTopByVoteCount(@Param("limit") int limit);

    List<Novel> findLatest(@Param("limit") int limit);

    int insert(Novel novel);

    int update(Novel novel);

    int deleteById(Long id);

    int incrementViewCount(Long id);

    int incrementVoteCount(Long id);

    int updateChapterCount(@Param("id") Long id, @Param("count") Integer count);

    int updateWordCount(@Param("id") Long id, @Param("count") Integer count);
}
