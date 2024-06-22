package tech.toshitworks.blog_app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.CategoryDto;
import tech.toshitworks.blog_app.service.CategoryService;
import tech.toshitworks.blog_app.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        return new ResponseEntity<>(categoryService.get(id), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<CategoryDto> getCategoryByTitle(@PathVariable String title){
        return new ResponseEntity<>(categoryService.get(title),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.create(categoryDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id){
        return new ResponseEntity<>(categoryService.update(categoryDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        categoryService.delete(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted",true),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
    }
}
