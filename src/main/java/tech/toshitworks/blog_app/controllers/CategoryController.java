package tech.toshitworks.blog_app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.CategoryDto;
import tech.toshitworks.blog_app.service.CategoryService;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.CategoryRoutes;

import java.util.List;

@RestController
@RequestMapping(CategoryRoutes.BASE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(CategoryRoutes.GET_BY_ID)
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.get(id), HttpStatus.OK);
    }

    @GetMapping(CategoryRoutes.GET_BY_TITLE)
    public ResponseEntity<List<CategoryDto>> getCategoryByTitle(@PathVariable String title) {
        return new ResponseEntity<>(categoryService.get(title), HttpStatus.OK);
    }

    @PostMapping(CategoryRoutes.CREATE)
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(CategoryRoutes.UPDATE)
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.update(categoryDto, id), HttpStatus.OK);
    }

    @DeleteMapping(CategoryRoutes.DELETE)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(new ApiResponse("Category Deleted", true), HttpStatus.OK);
    }

    @GetMapping(CategoryRoutes.GET_ALL)
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }
}
