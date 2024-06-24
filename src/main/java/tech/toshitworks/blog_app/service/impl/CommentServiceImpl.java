package tech.toshitworks.blog_app.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import tech.toshitworks.blog_app.entity.Comment;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.entity.User;
import tech.toshitworks.blog_app.exceptions.ResourceNotFoundException;
import tech.toshitworks.blog_app.mapper.CommentMapper;
import tech.toshitworks.blog_app.mapper.PostMapper;
import tech.toshitworks.blog_app.mapper.UserMapper;
import tech.toshitworks.blog_app.payloads.CommentDto;
import tech.toshitworks.blog_app.repository.CommentRepository;
import tech.toshitworks.blog_app.repository.PostRepository;
import tech.toshitworks.blog_app.repository.UserRepository;
import tech.toshitworks.blog_app.security.JWTTokenHelper;
import tech.toshitworks.blog_app.service.CommentService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,UserMapper userMapper,PostMapper postMapper,UserRepository userRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto create(CommentDto commentDto,Integer postId,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId.toString()));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId.toString()));
        commentDto.setDate(new Date());
        commentDto.setUser(userMapper.toUserDto(user));
        commentDto.setPost(postMapper.toPostDto(post));
        Comment savedComment = commentRepository.save(commentMapper.toComment(commentDto));
        return commentMapper.toCommentDto(savedComment);
    }

    @Override
    public CommentDto update(CommentDto commentDto,Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",id.toString()));
        comment.setDate(new Date());
        comment.setContent(commentDto.getContent());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void delete(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",id.toString()));
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId.toString()));
        List<Comment> comments = commentRepository.findByUser(user);
        return comments.stream().map(commentMapper::toCommentDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getByPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId.toString()));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(commentMapper::toCommentDto).collect(Collectors.toList());
    }


}
