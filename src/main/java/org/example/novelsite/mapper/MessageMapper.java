package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Message;
import java.util.List;

@Mapper
public interface MessageMapper {

    Message findById(Long id);

    List<Message> findAll();

    List<Message> findByReaderId(Long readerId);

    List<Message> findByAuthorId(Long authorId);

    int insert(Message message);

    int reply(@Param("id") Long id, @Param("replyContent") String replyContent);

    int deleteById(Long id);
}
