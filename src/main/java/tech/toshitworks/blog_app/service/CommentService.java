package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.entity.User;
import tech.toshitworks.blog_app.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(CommentDto commentDto,Integer postId,Integer userId);

    CommentDto update(CommentDto commentDto,Integer id);

    void delete(Integer id);

    List<CommentDto> getByUser(Integer userId);

    List<CommentDto> getByPost(Integer postId);
}
