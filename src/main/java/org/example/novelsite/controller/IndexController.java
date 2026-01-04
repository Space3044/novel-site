package org.example.novelsite.controller;

import org.example.novelsite.entity.Novel;
import org.example.novelsite.service.CategoryService;
import org.example.novelsite.service.NovelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final NovelService novelService;
    private final CategoryService categoryService;

    public IndexController(NovelService novelService, CategoryService categoryService) {
        this.novelService = novelService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Novel> hotNovels = novelService.findTopByViewCount(10);
        List<Novel> latestNovels = novelService.findLatest(10);
        List<Novel> topVoteNovels = novelService.findTopByVoteCount(10);

        model.addAttribute("hotNovels", hotNovels);
        model.addAttribute("latestNovels", latestNovels);
        model.addAttribute("topVoteNovels", topVoteNovels);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", "home");

        return "index";
    }
}
