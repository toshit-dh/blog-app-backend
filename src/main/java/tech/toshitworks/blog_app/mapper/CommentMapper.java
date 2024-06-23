package tech.toshitworks.blog_app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tech.toshitworks.blog_app.entity.Comment;
import tech.toshitworks.blog_app.payloads.CommentDto;

@Component
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Comment toComment(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }
    public CommentDto toCommentDto(Comment comment){
        return modelMapper.map(comment,CommentDto.class);
    }
}
