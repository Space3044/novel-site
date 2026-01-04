package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Bookmark;
import org.example.novelsite.mapper.BookmarkMapper;
import org.example.novelsite.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkMapper bookmarkMapper;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper) {
        this.bookmarkMapper = bookmarkMapper;
    }

    @Override
    public List<Bookmark> findByReaderId(Long readerId) {
        return bookmarkMapper.findByReaderId(readerId);
    }

    @Override
    public boolean isBookmarked(Long readerId, Long novelId) {
        return bookmarkMapper.findByReaderAndNovel(readerId, novelId) != null;
    }

    @Override
    public boolean add(Long readerId, Long novelId) {
        if (isBookmarked(readerId, novelId)) {
            return true;
        }
        Bookmark bookmark = new Bookmark();
        bookmark.setReaderId(readerId);
        bookmark.setNovelId(novelId);
        return bookmarkMapper.insert(bookmark) > 0;
    }

    @Override
    public boolean remove(Long readerId, Long novelId) {
        return bookmarkMapper.deleteByReaderAndNovel(readerId, novelId) > 0;
    }
}
