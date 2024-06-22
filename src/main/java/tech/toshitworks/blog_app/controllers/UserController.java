package tech.toshitworks.blog_app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.UserDto;
import tech.toshitworks.blog_app.service.UserService;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.UserRoutes;

import java.util.List;

@RestController
@RequestMapping(UserRoutes.BASE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(UserRoutes.GET_BY_ID)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping(UserRoutes.GET_BY_NAME)
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.get(name), HttpStatus.OK);
    }

    @PostMapping(UserRoutes.CREATE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @PutMapping(UserRoutes.UPDATE)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.OK);
    }

    @DeleteMapping(UserRoutes.DELETE)
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted", true), HttpStatus.OK);
    }

    @GetMapping(UserRoutes.GET_ALL)
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
