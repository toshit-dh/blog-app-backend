package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.payloads.PostDto;

import java.util.List;

public interface PostService {

    public PostDto create(PostDto postDto);

    public PostDto get(Integer id);

    public PostDto get(String title);

    public PostDto update(PostDto postDto,Integer id);

    public void delete(Integer id);

    public List<PostDto> getAll();


}
