package tech.toshitworks.blog_app.service.impl;

import org.springframework.stereotype.Service;
import tech.toshitworks.blog_app.entity.User;
import tech.toshitworks.blog_app.exceptions.ResourceNotFoundException;
import tech.toshitworks.blog_app.mapper.UserMapper;
import tech.toshitworks.blog_app.payloads.UserDto;
import tech.toshitworks.blog_app.repository.UserRepository;
import tech.toshitworks.blog_app.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServioceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServioceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User savedUser = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto,Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
        userRepository.delete(user);
    }

    @Override
    public UserDto get(Integer id) {
        return userMapper.toUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString())));
    }

    @Override
    public UserDto get(String name) {
        return userMapper.toUserDto(userRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("User","Name",name)));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
}
