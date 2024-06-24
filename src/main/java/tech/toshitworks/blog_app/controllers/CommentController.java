package tech.toshitworks.blog_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.CommentDto;
import tech.toshitworks.blog_app.security.JWTTokenHelper;
import tech.toshitworks.blog_app.service.CommentService;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.CommentRoutes;

import java.util.List;

@RestController
@RequestMapping(CommentRoutes.BASE)
public class CommentController {

    private final CommentService commentService;
    private final JWTTokenHelper jwtTokenHelper;

    public CommentController(CommentService commentService,JWTTokenHelper jwtTokenHelper) {
        this.commentService = commentService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @GetMapping(CommentRoutes.GET_BY_USER)
    public ResponseEntity<List<CommentDto>> getCommentByUser(HttpServletRequest request){
        Long id = extractIdFromRequest(request);
        return new ResponseEntity<>(commentService.getByUser(id), HttpStatus.OK);
    }

    @GetMapping(CommentRoutes.GET_BY_POST)
    public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable Integer id){
        return new ResponseEntity<>(commentService.getByPost(id), HttpStatus.OK);
    }

    @PostMapping(CommentRoutes.CREATE)
    public ResponseEntity<CommentDto> createComment(HttpServletRequest request,@Valid @RequestBody CommentDto commentDto,@PathVariable Integer postId){
        Long userId = extractIdFromRequest(request);
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

    private Long extractIdFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token =  authorizationHeader.substring(7);
            return jwtTokenHelper.extractUserId(token);
        }
        return null;
    }

}
