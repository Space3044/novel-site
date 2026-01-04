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
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderService readerService;
    private final BookmarkService bookmarkService;
    private final MessageService messageService;
    private final CommentService commentService;

    public ReaderController(ReaderService readerService, BookmarkService bookmarkService,
                           MessageService messageService, CommentService commentService) {
        this.readerService = readerService;
        this.bookmarkService = bookmarkService;
        this.messageService = messageService;
        this.commentService = commentService;
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }
        model.addAttribute("reader", reader);
        model.addAttribute("currentPage", "profile");
        return "reader/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String nickname,
                               @RequestParam(required = false) String email,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }

        reader.setNickname(nickname);
        reader.setEmail(email);
        readerService.update(reader);
        session.setAttribute("reader", reader);

        redirectAttributes.addFlashAttribute("success", "资料更新成功");
        return "redirect:/reader/profile";
    }

    @GetMapping("/bookmarks")
    public String bookmarks(HttpSession session, Model model) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }

        List<Bookmark> bookmarks = bookmarkService.findByReaderId(reader.getId());
        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("currentPage", "bookmarks");
        return "reader/bookmarks";
    }

    @GetMapping("/messages")
    public String messages(HttpSession session, Model model) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }

        List<Message> messages = messageService.findByReaderId(reader.getId());
        model.addAttribute("messages", messages);
        model.addAttribute("currentPage", "messages");
        return "reader/messages";
    }

    @PostMapping("/message/send")
    public String sendMessage(@RequestParam String content,
                             @RequestParam(required = false) Long authorId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/login";
        }

        Message message = new Message();
        message.setReaderId(reader.getId());
        message.setAuthorId(authorId);
        message.setContent(content);
        messageService.save(message);

        redirectAttributes.addFlashAttribute("success", "留言发送成功");
        return "redirect:/reader/messages";
    }
}
