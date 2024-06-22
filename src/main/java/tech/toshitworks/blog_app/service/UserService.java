package tech.toshitworks.blog_app.service;

import tech.toshitworks.blog_app.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto,Integer id);

    void delete(Integer id);

    UserDto get(Integer id);

    UserDto get(String name);

    List<UserDto> getAll();


}
