# Novel Site - 在线小说托管网站

Novel Site 是一个基于 Spring Boot 的在线小说托管网站，采用标准的 MVC 架构模式，使用 Java 11、Spring Boot 2.7.18、MyBatis、SQLite 等技术栈开发。项目实现了用户管理、小说发布、章节阅读、评论互动等完整功能。

## 技术栈

- **编程语言**：Java 11
- **框架**：Spring Boot 2.7.18
- **数据访问**：MyBatis 2.3.2
- **数据库**：SQLite
- **模板引擎**：Thymeleaf
- **构建工具**：Maven
- **辅助工具**：Lombok

## 功能特性

### 用户管理
- 读者和作者注册/登录/登出
- 个人信息管理
- 权限控制

### 小说管理
- 小说发布、编辑、删除
- 小说分类管理（玄幻、武侠、都市、历史、科幻、悬疑等）
- 小说搜索和筛选功能

### 章节管理
- 章节发布、编辑、删除
- 章节顺序管理
- 章节内容展示

### 阅读功能
- 章节在线阅读
- 阅读历史记录
- 书签功能
- 章节导航

### 互动功能
- 评论发布和查看
- 小说评分功能
- 用户消息系统
- 小说收藏功能

### 管理功能
- 管理员后台管理
- 用户管理
- 内容审核功能

## 项目架构

项目采用经典的三层架构设计：

```
表现层 (Controller)
    ↓
业务逻辑层 (Service)
    ↓
数据访问层 (Mapper/Data Access Layer)
```

### 1. 表现层 (Controller)

位于 `src/main/java/org/example/novelsite/controller/`

- **AuthController.java**：处理用户认证相关请求（登录、注册、登出等）
- **FileUploadController.java**：处理文件上传请求（如小说封面等）
- **AdminController.java**：处理管理员后台功能
- **IndexController.java**：处理首页展示请求
- **NovelController.java**：处理小说相关请求（列表、详情、搜索等）
- **ReaderController.java**：处理读者个人中心等功能

### 2. 业务逻辑层 (Service)

位于 `src/main/java/org/example/novelsite/service/`（接口定义）

- **ReaderService.java**：读者业务逻辑接口
- **AuthorService.java**：作者业务逻辑接口
- **NovelService.java**：小说业务逻辑接口
- **ChapterService.java**：章节业务逻辑接口
- **CommentService.java**：评论业务逻辑接口
- **BookmarkService.java**：书签业务逻辑接口
- **MessageService.java**：消息业务逻辑接口
- **CategoryService.java**：分类业务逻辑接口

位于 `src/main/java/org/example/novelsite/service/impl/`（实现类）

- **ReaderServiceImpl.java**：读者业务逻辑实现
- **AuthorServiceImpl.java**：作者业务逻辑实现
- **NovelServiceImpl.java**：小说业务逻辑实现
- **ChapterServiceImpl.java**：章节业务逻辑实现
- **CommentServiceImpl.java**：评论业务逻辑实现
- **BookmarkServiceImpl.java**：书签业务逻辑实现
- **MessageServiceImpl.java**：消息业务逻辑实现
- **CategoryServiceImpl.java**：分类业务逻辑实现

### 3. 数据访问层 (Mapper)

位于 `src/main/java/org/example/novelsite/mapper/`（接口定义）

- **ReaderMapper.java**：读者数据访问接口
- **AuthorMapper.java**：作者数据访问接口
- **NovelMapper.java**：小说数据访问接口
- **ChapterMapper.java**：章节数据访问接口
- **CommentMapper.java**：评论数据访问接口
- **BookmarkMapper.java**：书签数据访问接口
- **MessageMapper.java**：消息数据访问接口
- **CategoryMapper.java**：分类数据访问接口

位于 `src/main/resources/mapper/`（MyBatis XML 映射文件）

- **ReaderMapper.xml**：读者数据访问 SQL 映射
- **AuthorMapper.xml**：作者数据访问 SQL 映射
- **NovelMapper.xml**：小说数据访问 SQL 映射
- **ChapterMapper.xml**：章节数据访问 SQL 映射
- **CommentMapper.xml**：评论数据访问 SQL 映射
- **BookmarkMapper.xml**：书签数据访问 SQL 映射
- **MessageMapper.xml**：消息数据访问 SQL 映射
- **CategoryMapper.xml**：分类数据访问 SQL 映射

### 4. 实体层 (Entity)

位于 `src/main/java/org/example/novelsite/entity/`

- **Reader.java**：读者实体类，对应 reader 表
- **Author.java**：作者实体类，对应 author 表
- **Novel.java**：小说实体类，对应 novel 表
- **Chapter.java**：章节实体类，对应 chapter 表
- **Comment.java**：评论实体类，对应 comment 表
- **Bookmark.java**：书签实体类，对应 bookmark 表
- **Message.java**：消息实体类，对应 message 表
- **Category.java**：分类实体类，对应 category 表

### 5. 配置层 (Config)

位于 `src/main/java/org/example\novelsite\config/`

- **DatabaseConfig.java**：数据库配置类，负责初始化 SQLite 数据库
- **WebMvcConfig.java**：Web MVC 配置类，处理静态资源等配置

### 6. 资源文件

#### 配置文件
- **src/main/resources/application.properties**：Spring Boot 配置文件
- **src/main/resources/schema.sql**：数据库表结构定义和初始化数据

#### 前端资源
- **src/main/resources/static/css/style.css**：全局样式文件
- **src/main/resources/static/js/main.js**：前端 JavaScript 文件

#### 模板文件
- **src/main/resources/templates/index.html**：首页模板
- **src/main/resources/templates/login.html**：登录页面模板
- **src/main/resources/templates/register.html**：注册页面模板
- **src/main/resources/templates/novel/list.html**：小说列表页面模板
- **src/main/resources/templates/novel/detail.html**：小说详情页面模板
- **src/main/resources/templates/novel/read.html**：小说阅读页面模板
- **src/main/resources/templates/reader/profile.html**：读者个人中心页面模板
- **src/main/resources/templates/reader/bookmarks.html**：读者书签页面模板
- **src/main/resources/templates/reader/messages.html**：读者消息页面模板
- **src/main/resources/templates/admin/**：管理员后台相关页面模板
- **src/main/resources/templates/fragments/**：页面片段模板

## 数据库设计

使用 SQLite 数据库 (novel.db)，包含以下主要表：

1. **reader**：读者信息表
2. **author**：作者信息表
3. **category**：小说分类表
4. **novel**：小说信息表
5. **chapter**：章节信息表
6. **comment**：评论信息表
7. **bookmark**：书签信息表
8. **reading_history**：阅读记录表
9. **message**：消息信息表

## 依赖注入模式

项目采用构造函数注入（Constructor Injection）的方式进行依赖注入，这符合 Spring 框架的最佳实践，有助于：

- 避免循环依赖
- 使依赖关系更清晰
- 提高代码的可测试性
- 确保依赖在对象创建时就处于可用状态

## 项目配置文件

- **pom.xml**：Maven 项目配置文件，定义项目依赖和构建配置
- **mvnw/mvnw.cmd**：Maven Wrapper，用于在没有预安装 Maven 的环境中运行 Maven
- **.gitignore**：Git 忽略文件配置
- **.gitattributes**：Git 属性配置

## 项目启动入口

- **NovelSiteApplication.java**：Spring Boot 应用启动类，项目入口点

## 项目构建目录

- **target/**：Maven 构建输出目录，包含编译后的 class 文件、jar 包等
- **uploads/**：文件上传目录，用于存储上传的小说封面等文件

## 快速开始

### 环境要求

- Java 11 或更高版本
- Maven 3.6 或更高版本

### 构建项目

```bash
./mvnw clean compile
```

### 运行项目

```bash
./mvnw spring-boot:run
```

应用将启动在 `http://localhost:8080`

### 打包项目

```bash
./mvnw clean package
```

生成的 JAR 文件位于 `target/` 目录下

### 运行打包后的应用

```bash
java -jar target/novel-site-0.0.1-SNAPSHOT.jar
```

## 项目结构

```
novel-site/
├── src/
│   ├── main/
│   │   ├── java/org/example/novelsite/
│   │   │   ├── controller/     # 控制器层
│   │   │   ├── service/        # 服务层
│   │   │   │   └── impl/       # 服务实现层
│   │   │   ├── mapper/         # 数据访问层
│   │   │   ├── entity/         # 实体类
│   │   │   └── config/         # 配置类
│   │   └── resources/
│   │       ├── mapper/         # MyBatis映射文件
│   │       ├── templates/      # Thymeleaf模板
│   │       ├── static/         # 静态资源
│   │       └── application.properties  # 配置文件
│   └── test/
│       └── java/               # 测试代码
├── uploads/                    # 上传文件目录
├── target/                     # 构建输出目录
├── pom.xml                     # Maven配置文件
├── mvnw, mvnw.cmd             # Maven Wrapper
├── README.md                   # 项目说明
├── .gitignore                 # Git忽略配置
└── schema.sql                  # 数据库初始化脚本
```

## 管理员账户

系统默认创建了管理员账户：
- 用户名：admin
- 密码：admin123
