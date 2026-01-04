package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Comment;
import org.example.novelsite.mapper.CommentMapper;
import org.example.novelsite.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment findById(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> findByNovelId(Long novelId) {
        return commentMapper.findByNovelId(novelId);
    }

    @Override
    public List<Comment> findByReaderId(Long readerId) {
        return commentMapper.findByReaderId(readerId);
    }

    @Override
    public boolean save(Comment comment) {
        if (comment.getRating() == null) {
            comment.setRating(5);
        }
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return commentMapper.deleteById(id) > 0;
    }
}
