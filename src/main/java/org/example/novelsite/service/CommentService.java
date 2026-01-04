package org.example.novelsite.service;

import org.example.novelsite.entity.Comment;
import java.util.List;

public interface CommentService {

    Comment findById(Long id);

    List<Comment> findByNovelId(Long novelId);

    List<Comment> findByReaderId(Long readerId);

    boolean save(Comment comment);

    boolean deleteById(Long id);
}
