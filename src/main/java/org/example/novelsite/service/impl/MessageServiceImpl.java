package org.example.novelsite.service.impl;

import org.example.novelsite.entity.Message;
import org.example.novelsite.mapper.MessageMapper;
import org.example.novelsite.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.findAll();
    }

    @Override
    public List<Message> findByReaderId(Long readerId) {
        return messageMapper.findByReaderId(readerId);
    }

    @Override
    public List<Message> findByAuthorId(Long authorId) {
        return messageMapper.findByAuthorId(authorId);
    }

    @Override
    public boolean save(Message message) {
        return messageMapper.insert(message) > 0;
    }

    @Override
    public boolean reply(Long id, String replyContent) {
        return messageMapper.reply(id, replyContent) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return messageMapper.deleteById(id) > 0;
    }
}
