package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto,Long id);

    void delete(Long id);

    UserDto get(Long id);

    UserDto get(String name);

    List<UserDto> getAll();


}
