package tech.toshitworks.blog_app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.CommentDto;
import tech.toshitworks.blog_app.service.CommentService;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.CommentRoutes;

import java.util.List;

@RestController
@RequestMapping(CommentRoutes.BASE)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(CommentRoutes.GET_BY_USER)
    public ResponseEntity<List<CommentDto>> getCommentByUser(@PathVariable Integer id){
        return new ResponseEntity<>(commentService.getByUser(id), HttpStatus.OK);
    }

    @GetMapping(CommentRoutes.GET_BY_POST)
    public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable Integer id){
        return new ResponseEntity<>(commentService.getByPost(id), HttpStatus.OK);
    }

    @PostMapping(CommentRoutes.CREATE)
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Integer postId,@PathVariable Integer userId){
        return new ResponseEntity<>(commentService.create(commentDto,postId,userId), HttpStatus.OK);
    }

    @PutMapping(CommentRoutes.UPDATE)
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Integer id){
        return new ResponseEntity<>(commentService.update(commentDto,id), HttpStatus.OK);
    }

    @DeleteMapping(CommentRoutes.DELETE)
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        commentService.delete(id);
        return new ResponseEntity<>(new ApiResponse(CommentRoutes.COMMENT_DELETED,true),HttpStatus.OK);
    }

}
