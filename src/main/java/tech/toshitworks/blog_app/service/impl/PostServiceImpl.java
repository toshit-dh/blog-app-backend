package tech.toshitworks.blog_app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.toshitworks.blog_app.entity.Category;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.entity.User;
import tech.toshitworks.blog_app.exceptions.ResourceNotFoundException;
import tech.toshitworks.blog_app.mapper.CategoryMappper;
import tech.toshitworks.blog_app.mapper.PostMapper;
import tech.toshitworks.blog_app.payloads.PostDto;
import tech.toshitworks.blog_app.payloads.PostResponse;
import tech.toshitworks.blog_app.repository.CategoryRepository;
import tech.toshitworks.blog_app.repository.PostRepository;
import tech.toshitworks.blog_app.repository.UserRepository;
import tech.toshitworks.blog_app.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CategoryMappper categoryMappper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, CategoryMappper categoryMappper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.categoryMappper = categoryMappper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto create(PostDto postDto, Integer userId, Integer categoryId) {
        Post post = postMapper.toPost(postDto);
        post.setImage("default.png");
        post.setDate(new Date());
        post.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId.toString())));
        post.setCategory(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString())));
        Post savedPost = postRepository.save(post);
        return postMapper.toPostDto(savedPost);
    }

    @Override
    public PostDto get(Integer id) {
        return postMapper.toPostDto(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString())));
    }

    @Override
    public List<PostDto> getByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId.toString()));
        return postRepository.findByUser(user).stream().map(postMapper::toPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));
        return postRepository.findByCategory(category).stream().map(postMapper::toPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto get(String title) {
        return postMapper.toPostDto(postRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Post", "Title", title)));
    }

    @Override
    public PostDto update(PostDto postDto, Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
        post.setDate(new Date());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(categoryMappper.toCategory(postDto.getCategory()));
        postRepository.save(post);
        return postMapper.toPostDto(post);
    }

    @Override
    public void delete(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id.toString()));
        postRepository.delete(post);
    }

    @Override
    public PostResponse searchPost(String keyword, Integer pageNo, Integer pageSize, String sortBy, Boolean ascending) {
        Pageable pageable = PageRequest.of(pageNo, pageSize,ascending? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending());
        Page<Post> posts = postRepository.findByTitleContaining(keyword,pageable);
        List<PostDto> postDtos = posts.getContent().stream().map(postMapper::toPostDto).collect(Collectors.toList());
        return new PostResponse(postDtos,posts.getNumber(),posts.getSize(),posts.getTotalPages(),posts.getNumberOfElements(),posts.isLast());
    }

    @Override
    public PostResponse getAll(Integer pageNo, Integer pageSize,String sortBy,Boolean ascending) {
        Pageable pageable = PageRequest.of(pageNo, pageSize,ascending? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending());
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDto> postDtos = posts.getContent().stream().map(postMapper::toPostDto).collect(Collectors.toList());
        return new PostResponse(postDtos,posts.getNumber(),posts.getSize(),posts.getTotalPages(),posts.getNumberOfElements(),posts.isLast());
    }
}
