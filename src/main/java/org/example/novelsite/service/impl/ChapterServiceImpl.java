package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Chapter;
import org.example.novelsite.mapper.ChapterMapper;
import org.example.novelsite.mapper.NovelMapper;
import org.example.novelsite.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;
    private final NovelMapper novelMapper;

    public ChapterServiceImpl(ChapterMapper chapterMapper, NovelMapper novelMapper) {
        this.chapterMapper = chapterMapper;
        this.novelMapper = novelMapper;
    }

    @Override
    public Chapter findById(Long id) {
        return chapterMapper.findById(id);
    }

    @Override
    public List<Chapter> findByNovelId(Long novelId) {
        return chapterMapper.findByNovelId(novelId);
    }

    @Override
    public Chapter findByNovelIdAndOrder(Long novelId, Integer chapterOrder) {
        return chapterMapper.findByNovelIdAndOrder(novelId, chapterOrder);
    }

    @Override
    public Chapter findPrevChapter(Long novelId, Integer currentOrder) {
        return chapterMapper.findPrevChapter(novelId, currentOrder);
    }

    @Override
    public Chapter findNextChapter(Long novelId, Integer currentOrder) {
        return chapterMapper.findNextChapter(novelId, currentOrder);
    }

    @Override
    public boolean save(Chapter chapter) {
        if (chapter.getChapterOrder() == null) {
            int maxOrder = chapterMapper.getMaxOrder(chapter.getNovelId());
            chapter.setChapterOrder(maxOrder + 1);
        }
        if (chapter.getWordCount() == null && chapter.getContent() != null) {
            chapter.setWordCount(chapter.getContent().length());
        }
        boolean result = chapterMapper.insert(chapter) > 0;
        if (result) {
            updateNovelStats(chapter.getNovelId());
        }
        return result;
    }

    @Override
    public boolean update(Chapter chapter) {
        if (chapter.getWordCount() == null && chapter.getContent() != null) {
            chapter.setWordCount(chapter.getContent().length());
        }
        boolean result = chapterMapper.update(chapter) > 0;
        if (result) {
            Chapter existing = chapterMapper.findById(chapter.getId());
            if (existing != null) {
                updateNovelStats(existing.getNovelId());
            }
        }
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        Chapter chapter = chapterMapper.findById(id);
        boolean result = chapterMapper.deleteById(id) > 0;
        if (result && chapter != null) {
            updateNovelStats(chapter.getNovelId());
        }
        return result;
    }

    private void updateNovelStats(Long novelId) {
        List<Chapter> chapters = chapterMapper.findByNovelId(novelId);
        int chapterCount = chapters.size();
        int wordCount = chapters.stream().mapToInt(c -> c.getWordCount() != null ? c.getWordCount() : 0).sum();
        novelMapper.updateChapterCount(novelId, chapterCount);
        novelMapper.updateWordCount(novelId, wordCount);
    }
}
