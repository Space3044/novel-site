package org.example.novelsite.service;

import org.example.novelsite.entity.Author;
import java.util.List;

public interface AuthorService {

    Author findById(Long id);

    Author findByUsername(String username);

    List<Author> findAll();

    boolean register(Author author);

    Author login(String username, String password);

    boolean update(Author author);

    boolean deleteById(Long id);
}
