package com.pk.hotelmanagement.hotel.room.comment;

import com.pk.hotelmanagement.hotel.storage.product.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentData commentData) {
        commentService.createComment(commentData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
