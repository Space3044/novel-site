package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Reader;
import org.example.novelsite.mapper.ReaderMapper;
import org.example.novelsite.service.ReaderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderMapper readerMapper;

    public ReaderServiceImpl(ReaderMapper readerMapper) {
        this.readerMapper = readerMapper;
    }

    @Override
    public Reader findById(Long id) {
        return readerMapper.findById(id);
    }

    @Override
    public Reader findByUsername(String username) {
        return readerMapper.findByUsername(username);
    }

    @Override
    public List<Reader> findAll() {
        return readerMapper.findAll();
    }

    @Override
    public boolean register(Reader reader) {
        if (readerMapper.findByUsername(reader.getUsername()) != null) {
            return false;
        }
        return readerMapper.insert(reader) > 0;
    }

    @Override
    public Reader login(String username, String password) {
        Reader reader = readerMapper.findByUsername(username);
        if (reader != null && reader.getPassword().equals(password)) {
            return reader;
        }
        return null;
    }

    @Override
    public boolean update(Reader reader) {
        return readerMapper.update(reader) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return readerMapper.deleteById(id) > 0;
    }
}
