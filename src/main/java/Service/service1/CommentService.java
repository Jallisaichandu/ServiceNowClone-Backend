package Service.service1;

import Service.entity.Comment;
import Service.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.repository.TicketRepository;
import Service.entity.Ticket;

import org.springframework.security.core.context.SecurityContextHolder;
import Service.repository.UserRepository;
import Service.entity.User;
import java.time.LocalDateTime;


import java.util.List;



@Service
public class CommentService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }

    public List<Comment> getCommentsByTicket(Long ticketId) {

        return commentRepository.findByTicketId(ticketId);

    }

    public Comment addComment(Long ticketId, String message) {


        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket Not Found"));

        Comment comment = new Comment();

        comment.setMessage(message);

        comment.setCreatedDate(LocalDateTime.now());

        comment.setTicket(ticket);
        Comment savedComment = commentRepository.save(comment);
        if (ticket.getAssignedUser() != null) {

            emailService.sendEmail(
                    ticket.getAssignedUser().getEmail(),
                    "New Comment Added - ServiceNow Clone",
                    "Hello " + ticket.getAssignedUser().getName() + ",\n\n" +
                            "A new comment has been added to your ticket.\n\n" +
                            "Ticket Title : " + ticket.getTitle() + "\n\n" +
                            "Comment : " + savedComment.getMessage() + "\n\n" +
                            "Please log in to the application to view the comment.\n\n" +
                            "Regards,\nServiceNow Clone"
            );


            String email = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            User user = userRepository.findByEmail(email);

            comment.setUserName(user.getName());

            return commentRepository.save(comment);

        }
        return savedComment;



    }
}