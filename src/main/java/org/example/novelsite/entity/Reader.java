package org.example.novelsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Reader {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
