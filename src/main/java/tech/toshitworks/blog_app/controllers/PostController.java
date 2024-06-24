package tech.toshitworks.blog_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.PostDto;
import tech.toshitworks.blog_app.payloads.PostResponse;
import tech.toshitworks.blog_app.security.JWTTokenHelper;
import tech.toshitworks.blog_app.service.PostImageService;
import tech.toshitworks.blog_app.service.PostService;
import tech.toshitworks.blog_app.utils.Constants.Pagination;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.PostRoutes;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(PostRoutes.BASE)
public class PostController {

    private final PostService postService;
    private final JWTTokenHelper jwtTokenHelper;
    @Value("${project.image}")
    private String path;

    public PostController(PostService postService,JWTTokenHelper jwtTokenHelper) {
        this.postService = postService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @PostMapping(PostRoutes.CREATE)
    public ResponseEntity<PostDto> createPost(HttpServletRequest request,@Valid @RequestBody PostDto postDto, @PathVariable Integer categoryId) {
        Long userId = extractIdFromRequest(request);
        return new ResponseEntity<>(postService.create(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping(PostRoutes.GET_BY_ID)
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
        return new ResponseEntity<>(postService.get(id), HttpStatus.OK);
    }

    @GetMapping(PostRoutes.GET_BY_USER)
    public ResponseEntity<List<PostDto>> getPostsByUser(HttpServletRequest request) {
        Long id = extractIdFromRequest(request);
        return new ResponseEntity<>(postService.getByUser(id), HttpStatus.OK);
    }

    @GetMapping(PostRoutes.GET_BY_CATEGORY)
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(postService.getByCategory(id), HttpStatus.OK);
    }

    @GetMapping(PostRoutes.GET_BY_TITLE)
    public ResponseEntity<PostDto> getPostByTitle(@PathVariable String title) {
        return new ResponseEntity<>(postService.get(title), HttpStatus.OK);
    }

    @PutMapping(PostRoutes.UPDATE)
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer id) {
        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping(PostRoutes.DELETE)
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        postService.delete(id);
        return new ResponseEntity<>(new ApiResponse(PostRoutes.POST_DELETED, true), HttpStatus.OK);
    }

    @GetMapping(PostRoutes.GET_ALL)
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = Pagination.Value.PAGE_NUMBER, defaultValue = Pagination.DefaultValue.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = Pagination.Value.PAGE_SIZE, defaultValue = Pagination.DefaultValue.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = Pagination.Value.SORT_BY, defaultValue = Pagination.DefaultValue.SORT_BY, required = false) String sortBy,
            @RequestParam(value = Pagination.Value.ASCENDING, defaultValue = Pagination.DefaultValue.ASCENDING, required = false) Boolean ascending
    ) {
        return new ResponseEntity<>(postService.getAll(pageNo, pageSize, sortBy, ascending), HttpStatus.OK);
    }

    @GetMapping(PostRoutes.SEARCH)
    public ResponseEntity<PostResponse> getSearchedPosts(
            @PathVariable String keyword,
            @RequestParam(value = Pagination.Value.PAGE_NUMBER, defaultValue = Pagination.DefaultValue.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = Pagination.Value.PAGE_SIZE, defaultValue = Pagination.DefaultValue.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = Pagination.Value.SORT_BY, defaultValue = Pagination.DefaultValue.SORT_BY, required = false) String sortBy,
            @RequestParam(value = Pagination.Value.ASCENDING, defaultValue = Pagination.DefaultValue.ASCENDING, required = false) Boolean ascending
    ) {
        return new ResponseEntity<>(postService.searchPost(keyword, pageNo, pageSize, sortBy, ascending), HttpStatus.OK);
    }

    @PostMapping(PostRoutes.UPLOAD_IMAGE)
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam(PostRoutes.REQUEST_IMAGE)MultipartFile file,@PathVariable Integer id) throws IOException {
        return new ResponseEntity<>(postService.saveImage(file,id),HttpStatus.OK);
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
