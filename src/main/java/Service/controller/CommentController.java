package Service.controller;

import Service.entity.Comment;
import Service.service1.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Service.dto.CommentRequest;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/ticket/{ticketId}")
    public List<Comment> getCommentsByTicket(
            @PathVariable Long ticketId) {

        return commentService.getCommentsByTicket(ticketId);

    }
    @PostMapping("/{ticketId}")
    public Comment addComment(
            @PathVariable Long ticketId,

            @RequestBody CommentRequest request) {
        System.out.println("INSIDE ADD COMMENT");

        return commentService.addComment(
                ticketId,
                request.getMessage()
        );
    }
}