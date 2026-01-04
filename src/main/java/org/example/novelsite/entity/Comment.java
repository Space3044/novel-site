package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long novelId;
    private Long readerId;
    private String content;
    private Integer rating;
    private LocalDateTime createTime;

    // 关联字段
    private String readerName;
    private String novelName;
}
