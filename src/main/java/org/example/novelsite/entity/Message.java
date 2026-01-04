package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long readerId;
    private Long authorId;
    private String content;
    private String replyContent;
    private LocalDateTime replyTime;
    private LocalDateTime createTime;

    // 关联字段
    private String readerName;
    private String authorName;
}
