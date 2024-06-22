package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto create(CategoryDto categoryDto);

    public CategoryDto get(Integer id);

    public CategoryDto get(String title);

    public CategoryDto update(CategoryDto categoryDto,Integer id);

    public List<CategoryDto> getAll();

    public void delete(Integer id);
}
