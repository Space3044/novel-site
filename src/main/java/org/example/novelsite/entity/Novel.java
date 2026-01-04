package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Novel {
    private Long id;
    private String name;
    private Long authorId;
    private Long categoryId;
    private String cover;
    private String description;
    private Integer status; // 0: 连载中, 1: 已完结, 2: 暂停
    private Integer chapterCount;
    private Integer pageCount;
    private Integer voteCount;
    private Integer wordCount;
    private Integer viewCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 关联字段
    private String authorName;
    private String categoryName;
}
