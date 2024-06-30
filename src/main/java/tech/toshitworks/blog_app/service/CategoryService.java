package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto get(Integer id);

    List<CategoryDto> get(String title);

    CategoryDto update(CategoryDto categoryDto,Integer id);

    List<CategoryDto> getAll();

    void delete(Integer id);
}
