package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Chapter {
    private Long id;
    private Long novelId;
    private String title;
    private String content;
    private Integer wordCount;
    private Integer chapterOrder;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 关联字段
    private String novelName;
}
