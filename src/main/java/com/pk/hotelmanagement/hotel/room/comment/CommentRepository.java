package com.pk.hotelmanagement.hotel.room.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
