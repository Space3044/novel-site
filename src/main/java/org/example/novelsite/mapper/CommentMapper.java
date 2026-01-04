package org.example.novelsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.novelsite.entity.Comment;
import java.util.List;

@Mapper
public interface CommentMapper {

    Comment findById(Long id);

    List<Comment> findByNovelId(Long novelId);

    List<Comment> findByReaderId(Long readerId);

    int insert(Comment comment);

    int deleteById(Long id);

    int deleteByNovelId(Long novelId);
}
