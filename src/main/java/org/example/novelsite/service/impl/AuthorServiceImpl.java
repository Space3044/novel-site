package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Author;
import org.example.novelsite.mapper.AuthorMapper;
import org.example.novelsite.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    public Author findById(Long id) {
        return authorMapper.findById(id);
    }

    @Override
    public Author findByUsername(String username) {
        return authorMapper.findByUsername(username);
    }

    @Override
    public List<Author> findAll() {
        return authorMapper.findAll();
    }

    @Override
    public boolean register(Author author) {
        if (authorMapper.findByUsername(author.getUsername()) != null) {
            return false;
        }
        if (author.getLevel() == null) {
            author.setLevel(1);
        }
        if (author.getIsTransfer() == null) {
            author.setIsTransfer(0);
        }
        return authorMapper.insert(author) > 0;
    }

    @Override
    public Author login(String username, String password) {
        Author author = authorMapper.findByUsername(username);
        if (author != null && author.getPassword().equals(password)) {
            return author;
        }
        return null;
    }

    @Override
    public boolean update(Author author) {
        return authorMapper.update(author) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return authorMapper.deleteById(id) > 0;
    }
}
