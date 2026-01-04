-- 读者信息表
CREATE TABLE IF NOT EXISTS reader (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(100),
    avatar VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 作者信息表
CREATE TABLE IF NOT EXISTS author (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    introduction TEXT,
    level INTEGER DEFAULT 1,
    is_transfer INTEGER DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 小说分类表
CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

-- 小说信息表
CREATE TABLE IF NOT EXISTS novel (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    author_id INTEGER NOT NULL,
    category_id INTEGER,
    cover VARCHAR(255),
    description TEXT,
    status INTEGER DEFAULT 0,
    chapter_count INTEGER DEFAULT 0,
    page_count INTEGER DEFAULT 0,
    vote_count INTEGER DEFAULT 0,
    word_count INTEGER DEFAULT 0,
    view_count INTEGER DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- 小说章节表
CREATE TABLE IF NOT EXISTS chapter (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    novel_id INTEGER NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    word_count INTEGER DEFAULT 0,
    chapter_order INTEGER NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (novel_id) REFERENCES novel(id)
);

-- 小说评论表
CREATE TABLE IF NOT EXISTS comment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    novel_id INTEGER NOT NULL,
    reader_id INTEGER NOT NULL,
    content TEXT NOT NULL,
    rating INTEGER DEFAULT 5,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (novel_id) REFERENCES novel(id),
    FOREIGN KEY (reader_id) REFERENCES reader(id)
);

-- 读者收藏表
CREATE TABLE IF NOT EXISTS bookmark (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reader_id INTEGER NOT NULL,
    novel_id INTEGER NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reader_id) REFERENCES reader(id),
    FOREIGN KEY (novel_id) REFERENCES novel(id),
    UNIQUE(reader_id, novel_id)
);

-- 阅读记录表
CREATE TABLE IF NOT EXISTS reading_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reader_id INTEGER NOT NULL,
    novel_id INTEGER NOT NULL,
    chapter_id INTEGER,
    last_read_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reader_id) REFERENCES reader(id),
    FOREIGN KEY (novel_id) REFERENCES novel(id),
    FOREIGN KEY (chapter_id) REFERENCES chapter(id)
);

-- 留言板表
CREATE TABLE IF NOT EXISTS message (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reader_id INTEGER,
    author_id INTEGER,
    content TEXT NOT NULL,
    reply_content TEXT,
    reply_time DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 初始化分类数据
INSERT OR IGNORE INTO category (id, name, description) VALUES
(1, '玄幻', '玄幻奇幻类小说'),
(2, '武侠', '武侠仙侠类小说'),
(3, '都市', '都市言情类小说'),
(4, '历史', '历史军事类小说'),
(5, '科幻', '科幻未来类小说'),
(6, '悬疑', '悬疑推理类小说');

-- 初始化管理员账号 (用户名: admin, 密码: admin123)
INSERT OR IGNORE INTO author (id, username, password, name, introduction, level) VALUES
(1, 'admin', 'admin123', '系统管理员', '网站管理员账号', 99);