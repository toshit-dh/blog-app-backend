package tech.toshitworks.blog_app.service.impl;

import org.springframework.stereotype.Service;
import tech.toshitworks.blog_app.entity.Category;
import tech.toshitworks.blog_app.exceptions.ResourceNotFoundException;
import tech.toshitworks.blog_app.mapper.CategoryMappper;
import tech.toshitworks.blog_app.payloads.CategoryDto;
import tech.toshitworks.blog_app.repository.CategoryRepository;
import tech.toshitworks.blog_app.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMappper categoryMappper) {
        this.categoryRepository = categoryRepository;
        this.categoryMappper = categoryMappper;
    }

    private final CategoryRepository categoryRepository;
    private final CategoryMappper categoryMappper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category savedCategory =  categoryRepository.save(categoryMappper.toCategory(categoryDto));
        return categoryMappper.toCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto get(Integer id) {
        return categoryMappper.toCategoryDto(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Id",id.toString())));
    }

    @Override
    public List<CategoryDto> get(String title) {
        List<Category> categories = categoryRepository.findByTitleContaining(title);
        return categories.stream().map(categoryMappper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Id",id.toString()));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return categoryMappper.toCategoryDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(categoryMappper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Id",id.toString()));
        categoryRepository.delete(category);

    }
}
