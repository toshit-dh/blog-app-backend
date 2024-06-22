package tech.toshitworks.blog_app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.payloads.PostDto;

@Component
public class PostMapper {

    private final ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PostDto toPostDto (Post post){
        return modelMapper.map(post,PostDto.class);
    }

    public Post toPost (PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }
}
