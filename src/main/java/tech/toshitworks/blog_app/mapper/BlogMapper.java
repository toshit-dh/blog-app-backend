package tech.toshitworks.blog_app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class BlogMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
