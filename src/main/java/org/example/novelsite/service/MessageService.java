package org.example.novelsite.service;

import org.example.novelsite.entity.Message;
import java.util.List;

public interface MessageService {

    Message findById(Long id);

    List<Message> findAll();

    List<Message> findByReaderId(Long readerId);

    List<Message> findByAuthorId(Long authorId);

    boolean save(Message message);

    boolean reply(Long id, String replyContent);

    boolean deleteById(Long id);
}
