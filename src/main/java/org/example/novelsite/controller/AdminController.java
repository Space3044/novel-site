package org.example.novelsite.controller;

import org.example.novelsite.entity.*;
import org.example.novelsite.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AuthorService authorService;
    private final NovelService novelService;
    private final ChapterService chapterService;
    private final CategoryService categoryService;
    private final ReaderService readerService;
    private final MessageService messageService;

    public AdminController(AuthorService authorService, NovelService novelService,
                          ChapterService chapterService, CategoryService categoryService,
                          ReaderService readerService, MessageService messageService) {
        this.authorService = authorService;
        this.novelService = novelService;
        this.chapterService = chapterService;
        this.categoryService = categoryService;
        this.readerService = readerService;
        this.messageService = messageService;
    }

    private Author checkLogin(HttpSession session) {
        return (Author) session.getAttribute("author");
    }

    @GetMapping("")
    public String index(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        List<Novel> novels = novelService.findByAuthorId(author.getId());

        // 计算统计数据
        int totalChapters = 0;
        int totalViews = 0;
        int totalVotes = 0;
        for (Novel novel : novels) {
            totalChapters += novel.getChapterCount() != null ? novel.getChapterCount() : 0;
            totalViews += novel.getViewCount() != null ? novel.getViewCount() : 0;
            totalVotes += novel.getVoteCount() != null ? novel.getVoteCount() : 0;
        }

        model.addAttribute("novels", novels);
        model.addAttribute("author", author);
        model.addAttribute("totalChapters", totalChapters);
        model.addAttribute("totalViews", totalViews);
        model.addAttribute("totalVotes", totalVotes);
        model.addAttribute("currentPage", "dashboard");
        return "admin/index";
    }

    @GetMapping("/novel/list")
    public String novelList(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        List<Novel> novels = novelService.findByAuthorId(author.getId());
        model.addAttribute("novels", novels);
        model.addAttribute("currentPage", "novel");
        return "admin/novel/list";
    }

    @GetMapping("/novel/add")
    public String novelAddPage(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", "novel");
        return "admin/novel/add";
    }

    @PostMapping("/novel/add")
    public String novelAdd(@RequestParam String name,
                          @RequestParam Long categoryId,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) String cover,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = new Novel();
        novel.setName(name);
        novel.setAuthorId(author.getId());
        novel.setCategoryId(categoryId);
        novel.setDescription(description);
        novel.setCover(cover);
        novelService.save(novel);

        redirectAttributes.addFlashAttribute("success", "小说创建成功");
        return "redirect:/admin/novel/list";
    }

    @GetMapping("/novel/edit/{id}")
    public String novelEditPage(@PathVariable Long id, HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(id);
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        model.addAttribute("novel", novel);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", "novel");
        return "admin/novel/edit";
    }

    @PostMapping("/novel/edit/{id}")
    public String novelEdit(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam Long categoryId,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) String cover,
                           @RequestParam Integer status,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(id);
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        novel.setName(name);
        novel.setCategoryId(categoryId);
        novel.setDescription(description);
        novel.setCover(cover);
        novel.setStatus(status);
        novelService.update(novel);

        redirectAttributes.addFlashAttribute("success", "小说更新成功");
        return "redirect:/admin/novel/list";
    }

    @PostMapping("/novel/delete/{id}")
    public String novelDelete(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(id);
        if (novel != null && novel.getAuthorId().equals(author.getId())) {
            novelService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "小说删除成功");
        }
        return "redirect:/admin/novel/list";
    }

    @GetMapping("/chapter/list/{novelId}")
    public String chapterList(@PathVariable Long novelId, HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(novelId);
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        List<Chapter> chapters = chapterService.findByNovelId(novelId);
        model.addAttribute("novel", novel);
        model.addAttribute("chapters", chapters);
        model.addAttribute("currentPage", "novel");
        return "admin/chapter/list";
    }

    @GetMapping("/chapter/add/{novelId}")
    public String chapterAddPage(@PathVariable Long novelId, HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(novelId);
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        model.addAttribute("novel", novel);
        model.addAttribute("currentPage", "novel");
        return "admin/chapter/add";
    }

    @PostMapping("/chapter/add/{novelId}")
    public String chapterAdd(@PathVariable Long novelId,
                            @RequestParam String title,
                            @RequestParam String content,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Novel novel = novelService.findById(novelId);
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        Chapter chapter = new Chapter();
        chapter.setNovelId(novelId);
        chapter.setTitle(title);
        chapter.setContent(content);
        chapterService.save(chapter);

        redirectAttributes.addFlashAttribute("success", "章节添加成功");
        return "redirect:/admin/chapter/list/" + novelId;
    }

    @GetMapping("/chapter/edit/{id}")
    public String chapterEditPage(@PathVariable Long id, HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Chapter chapter = chapterService.findById(id);
        if (chapter == null) {
            return "redirect:/admin/novel/list";
        }

        Novel novel = novelService.findById(chapter.getNovelId());
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        model.addAttribute("chapter", chapter);
        model.addAttribute("novel", novel);
        model.addAttribute("currentPage", "novel");
        return "admin/chapter/edit";
    }

    @PostMapping("/chapter/edit/{id}")
    public String chapterEdit(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String content,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Chapter chapter = chapterService.findById(id);
        if (chapter == null) {
            return "redirect:/admin/novel/list";
        }

        Novel novel = novelService.findById(chapter.getNovelId());
        if (novel == null || !novel.getAuthorId().equals(author.getId())) {
            return "redirect:/admin/novel/list";
        }

        chapter.setTitle(title);
        chapter.setContent(content);
        chapterService.update(chapter);

        redirectAttributes.addFlashAttribute("success", "章节更新成功");
        return "redirect:/admin/chapter/list/" + chapter.getNovelId();
    }

    @PostMapping("/chapter/delete/{id}")
    public String chapterDelete(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Chapter chapter = chapterService.findById(id);
        if (chapter != null) {
            Novel novel = novelService.findById(chapter.getNovelId());
            if (novel != null && novel.getAuthorId().equals(author.getId())) {
                Long novelId = chapter.getNovelId();
                chapterService.deleteById(id);
                redirectAttributes.addFlashAttribute("success", "章节删除成功");
                return "redirect:/admin/chapter/list/" + novelId;
            }
        }
        return "redirect:/admin/novel/list";
    }

    @GetMapping("/readers")
    public String readerList(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        List<Reader> readers = readerService.findAll();
        model.addAttribute("readers", readers);
        model.addAttribute("currentPage", "readers");
        return "admin/readers";
    }

    @GetMapping("/messages")
    public String messageList(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        List<Message> messages = messageService.findByAuthorId(author.getId());
        model.addAttribute("messages", messages);
        model.addAttribute("currentPage", "messages");
        return "admin/messages";
    }

    @PostMapping("/message/reply/{id}")
    public String replyMessage(@PathVariable Long id,
                              @RequestParam String replyContent,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        messageService.reply(id, replyContent);
        redirectAttributes.addFlashAttribute("success", "回复成功");
        return "redirect:/admin/messages";
    }

    // 分类管理
    @GetMapping("/category/list")
    public String categoryList(HttpSession session, Model model) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", "category");
        return "admin/category/list";
    }

    @PostMapping("/category/add")
    public String categoryAdd(@RequestParam String name,
                             @RequestParam(required = false) String description,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryService.save(category);

        redirectAttributes.addFlashAttribute("success", "分类添加成功");
        return "redirect:/admin/category/list";
    }

    @PostMapping("/category/delete/{id}")
    public String categoryDelete(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Author author = checkLogin(session);
        if (author == null) {
            return "redirect:/login?type=author";
        }

        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "分类删除成功");
        return "redirect:/admin/category/list";
    }

    // 新增分类（AJAX）
    @PostMapping("/category/add-ajax")
    @ResponseBody
    public java.util.Map<String, Object> categoryAddAjax(@RequestParam String name,
                                                          @RequestParam(required = false) String description,
                                                          HttpSession session) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        Author author = checkLogin(session);
        if (author == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        boolean saved = categoryService.save(category);

        if (saved) {
            result.put("success", true);
            result.put("message", "分类添加成功");
            result.put("category", category);
        } else {
            result.put("success", false);
            result.put("message", "分类添加失败");
        }
        return result;
    }
}
