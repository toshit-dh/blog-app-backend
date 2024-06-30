package tech.toshitworks.blog_app.service;

import org.springframework.web.multipart.MultipartFile;
import tech.toshitworks.blog_app.payloads.PostDto;
import tech.toshitworks.blog_app.payloads.PostResponse;

import java.io.IOException;
import java.util.List;

public interface PostService{

    PostDto create(MultipartFile file,PostDto postDto,Long userId,Integer categoryId);

    PostDto get(Integer id);

    List<PostDto> getByUser(Long userId);

    List<PostDto> getByCategory(Integer categoryId);

    PostDto get(String title);

    PostDto update(PostDto postDto,Integer id);

    void delete(Integer id);

    PostResponse searchPost(String keyword,Integer pageNo, Integer pageSize,String sortBy,Boolean ascending);

    PostResponse getAll(Integer pageNo, Integer pageSize,String sortBy,Boolean ascending);


}
