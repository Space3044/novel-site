package org.example.novelsite.service;

import org.example.novelsite.entity.Novel;
import java.util.List;

public interface NovelService {

    Novel findById(Long id);

    List<Novel> findAll();

    List<Novel> findByAuthorId(Long authorId);

    List<Novel> findByCategoryId(Long categoryId);

    List<Novel> findByStatus(Integer status);

    List<Novel> search(String keyword);

    List<Novel> findTopByViewCount(int limit);

    List<Novel> findTopByVoteCount(int limit);

    List<Novel> findLatest(int limit);

    boolean save(Novel novel);

    boolean update(Novel novel);

    boolean deleteById(Long id);

    void incrementViewCount(Long id);

    void incrementVoteCount(Long id);
}
