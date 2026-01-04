package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Bookmark {
    private Long id;
    private Long readerId;
    private Long novelId;
    private LocalDateTime createTime;

    // 关联字段
    private Novel novel;
}
