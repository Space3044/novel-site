package org.example.novelsite.service;

import org.example.novelsite.entity.Reader;
import java.util.List;

public interface ReaderService {

    Reader findById(Long id);

    Reader findByUsername(String username);

    List<Reader> findAll();

    boolean register(Reader reader);

    Reader login(String username, String password);

    boolean update(Reader reader);

    boolean deleteById(Long id);
}
