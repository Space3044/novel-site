package org.example.novelsite.controller;

import org.example.novelsite.entity.*;
import org.example.novelsite.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/novel")
public class NovelController {

    private final NovelService novelService;
    private final ChapterService chapterService;
    private final CommentService commentService;
    private final BookmarkService bookmarkService;
    private final CategoryService categoryService;

    public NovelController(NovelService novelService, ChapterService chapterService,
                          CommentService commentService, BookmarkService bookmarkService,
                          CategoryService categoryService) {
        this.novelService = novelService;
        this.chapterService = chapterService;
        this.commentService = commentService;
        this.bookmarkService = bookmarkService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Long categoryId,
                      @RequestParam(required = false) String keyword,
                      Model model) {
        List<Novel> novels;
        if (keyword != null && !keyword.trim().isEmpty()) {
            novels = novelService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else if (categoryId != null) {
            novels = novelService.findByCategoryId(categoryId);
            model.addAttribute("currentCategory", categoryService.findById(categoryId));
        } else {
            novels = novelService.findAll();
        }
        model.addAttribute("novels", novels);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", "list");
        return "novel/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Novel novel = novelService.findById(id);
        if (novel == null) {
            return "redirect:/novel/list";
        }

        novelService.incrementViewCount(id);

        List<Chapter> chapters = chapterService.findByNovelId(id);
        List<Comment> comments = commentService.findByNovelId(id);

        model.addAttribute("novel", novel);
        model.addAttribute("chapters", chapters);
        model.addAttribute("comments", comments);

        Reader reader = (Reader) session.getAttribute("reader");
        if (reader != null) {
            boolean isBookmarked = bookmarkService.isBookmarked(reader.getId(), id);
            model.addAttribute("isBookmarked", isBookmarked);
        }

        return "novel/detail";
    }

    @GetMapping("/read/{novelId}/{chapterOrder}")
    public String read(@PathVariable Long novelId, @PathVariable Integer chapterOrder, Model model) {
        Novel novel = novelService.findById(novelId);
        Chapter chapter = chapterService.findByNovelIdAndOrder(novelId, chapterOrder);

        if (novel == null || chapter == null) {
            return "redirect:/novel/list";
        }

        Chapter prevChapter = chapterService.findPrevChapter(novelId, chapterOrder);
        Chapter nextChapter = chapterService.findNextChapter(novelId, chapterOrder);

        model.addAttribute("novel", novel);
        model.addAttribute("chapter", chapter);
        model.addAttribute("prevChapter", prevChapter);
        model.addAttribute("nextChapter", nextChapter);

        return "novel/read";
    }

    @PostMapping("/vote/{id}")
    @ResponseBody
    public String vote(@PathVariable Long id) {
        novelService.incrementVoteCount(id);
        return "success";
    }

    @PostMapping("/bookmark/add/{novelId}")
    @ResponseBody
    public String addBookmark(@PathVariable Long novelId, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "not_login";
        }
        bookmarkService.add(reader.getId(), novelId);
        return "success";
    }

    @PostMapping("/bookmark/remove/{novelId}")
    @ResponseBody
    public String removeBookmark(@PathVariable Long novelId, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "not_login";
        }
        bookmarkService.remove(reader.getId(), novelId);
        return "success";
    }

    @PostMapping("/comment/add")
    public String addComment(@RequestParam Long novelId,
                            @RequestParam String content,
                            @RequestParam(defaultValue = "5") Integer rating,
                            HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }

        Comment comment = new Comment();
        comment.setNovelId(novelId);
        comment.setReaderId(reader.getId());
        comment.setContent(content);
        comment.setRating(rating);
        commentService.save(comment);

        return "redirect:/novel/detail/" + novelId;
    }
}
