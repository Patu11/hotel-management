package com.pk.hotelmanagement.hotel.room.comment;

import com.pk.hotelmanagement.hotel.room.Room;
import com.pk.hotelmanagement.hotel.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private RoomService roomService;

    @Autowired
    public CommentService(CommentRepository commentRepository, RoomService roomService) {
        this.commentRepository = commentRepository;
        this.roomService = roomService;
    }

    @Transactional
    public void createComment(CommentData commentData) {
        Comment comment = new Comment();
        comment.setContent(commentData.getContent());
        comment.setAuthor(commentData.getAuthor());
        comment.setRating(commentData.getRating());

        Room room = roomService.getRoom(commentData.getRoomId());
        room.addComment(comment);

        commentRepository.save(comment);
    }
}
