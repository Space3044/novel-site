package org.example.novelsite.controller;

import org.example.novelsite.entity.Reader;
import org.example.novelsite.entity.Author;
import org.example.novelsite.service.ReaderService;
import org.example.novelsite.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final ReaderService readerService;
    private final AuthorService authorService;

    public AuthController(ReaderService readerService, AuthorService authorService) {
        this.readerService = readerService;
        this.authorService = authorService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "type", defaultValue = "reader") String type, Model model) {
        model.addAttribute("type", type);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam(value = "type", defaultValue = "reader") String type,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        if ("author".equals(type)) {
            Author author = authorService.login(username, password);
            if (author != null) {
                session.setAttribute("author", author);
                session.setAttribute("userType", "author");
                return "redirect:/admin";
            }
        } else {
            Reader reader = readerService.login(username, password);
            if (reader != null) {
                session.setAttribute("reader", reader);
                session.setAttribute("userType", "reader");
                return "redirect:/";
            }
        }
        redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
        return "redirect:/login?type=" + type;
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(value = "type", defaultValue = "reader") String type, Model model) {
        model.addAttribute("type", type);
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam(value = "type", defaultValue = "reader") String type,
                          @RequestParam(required = false) String nickname,
                          @RequestParam(required = false) String name,
                          @RequestParam(required = false) String email,
                          @RequestParam(required = false) String introduction,
                          RedirectAttributes redirectAttributes) {
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "两次密码输入不一致");
            return "redirect:/register?type=" + type;
        }

        boolean success;
        if ("author".equals(type)) {
            Author author = new Author();
            author.setUsername(username);
            author.setPassword(password);
            author.setName(name != null ? name : username);
            author.setIntroduction(introduction);
            success = authorService.register(author);
        } else {
            Reader reader = new Reader();
            reader.setUsername(username);
            reader.setPassword(password);
            reader.setNickname(nickname != null ? nickname : username);
            reader.setEmail(email);
            success = readerService.register(reader);
        }

        if (success) {
            redirectAttributes.addFlashAttribute("success", "注册成功，请登录");
            return "redirect:/login?type=" + type;
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名已存在");
            return "redirect:/register?type=" + type;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
