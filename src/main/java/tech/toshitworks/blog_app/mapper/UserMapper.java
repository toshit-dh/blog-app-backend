package tech.toshitworks.blog_app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tech.toshitworks.blog_app.entity.User;
import tech.toshitworks.blog_app.payloads.UserDto;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public User toUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto toUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

}
