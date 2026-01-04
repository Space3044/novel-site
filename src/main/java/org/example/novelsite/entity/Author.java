package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Author {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String introduction;
    private Integer level;
    private Integer isTransfer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
