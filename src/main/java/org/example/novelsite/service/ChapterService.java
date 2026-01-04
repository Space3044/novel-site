package org.example.novelsite.service;

import org.example.novelsite.entity.Chapter;
import java.util.List;

public interface ChapterService {

    Chapter findById(Long id);

    List<Chapter> findByNovelId(Long novelId);

    Chapter findByNovelIdAndOrder(Long novelId, Integer chapterOrder);

    Chapter findPrevChapter(Long novelId, Integer currentOrder);

    Chapter findNextChapter(Long novelId, Integer currentOrder);

    boolean save(Chapter chapter);

    boolean update(Chapter chapter);

    boolean deleteById(Long id);
}
