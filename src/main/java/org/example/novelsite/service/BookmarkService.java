package org.example.novelsite.service;

import org.example.novelsite.entity.Bookmark;
import java.util.List;

public interface BookmarkService {

    List<Bookmark> findByReaderId(Long readerId);

    boolean isBookmarked(Long readerId, Long novelId);

    boolean add(Long readerId, Long novelId);

    boolean remove(Long readerId, Long novelId);
}
