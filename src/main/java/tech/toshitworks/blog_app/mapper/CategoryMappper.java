package tech.toshitworks.blog_app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tech.toshitworks.blog_app.entity.Category;
import tech.toshitworks.blog_app.payloads.CategoryDto;

@Component
public class CategoryMappper {

    private final ModelMapper modelMapper;

    public CategoryMappper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDto toCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    public Category toCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

}
