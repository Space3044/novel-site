package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Chapter;
import org.example.novelsite.entity.Novel;
import org.example.novelsite.mapper.ChapterMapper;
import org.example.novelsite.mapper.NovelMapper;
import org.example.novelsite.service.NovelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService {

    private final NovelMapper novelMapper;
    private final ChapterMapper chapterMapper;

    public NovelServiceImpl(NovelMapper novelMapper, ChapterMapper chapterMapper) {
        this.novelMapper = novelMapper;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public Novel findById(Long id) {
        return novelMapper.findById(id);
    }

    @Override
    public List<Novel> findAll() {
        return novelMapper.findAll();
    }

    @Override
    public List<Novel> findByAuthorId(Long authorId) {
        return novelMapper.findByAuthorId(authorId);
    }

    @Override
    public List<Novel> findByCategoryId(Long categoryId) {
        return novelMapper.findByCategoryId(categoryId);
    }

    @Override
    public List<Novel> findByStatus(Integer status) {
        return novelMapper.findByStatus(status);
    }

    @Override
    public List<Novel> search(String keyword) {
        return novelMapper.search(keyword);
    }

    @Override
    public List<Novel> findTopByViewCount(int limit) {
        return novelMapper.findTopByViewCount(limit);
    }

    @Override
    public List<Novel> findTopByVoteCount(int limit) {
        return novelMapper.findTopByVoteCount(limit);
    }

    @Override
    public List<Novel> findLatest(int limit) {
        return novelMapper.findLatest(limit);
    }

    @Override
    public boolean save(Novel novel) {
        if (novel.getStatus() == null) {
            novel.setStatus(0);
        }
        return novelMapper.insert(novel) > 0;
    }

    @Override
    public boolean update(Novel novel) {
        return novelMapper.update(novel) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        chapterMapper.deleteByNovelId(id);
        return novelMapper.deleteById(id) > 0;
    }

    @Override
    public void incrementViewCount(Long id) {
        novelMapper.incrementViewCount(id);
    }

    @Override
    public void incrementVoteCount(Long id) {
        novelMapper.incrementVoteCount(id);
    }

    private void updateNovelStats(Long novelId) {
        List<Chapter> chapters = chapterMapper.findByNovelId(novelId);
        int chapterCount = chapters.size();
        int wordCount = chapters.stream().mapToInt(c -> c.getWordCount() != null ? c.getWordCount() : 0).sum();
        novelMapper.updateChapterCount(novelId, chapterCount);
        novelMapper.updateWordCount(novelId, wordCount);
    }
}
