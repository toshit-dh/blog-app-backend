package tech.toshitworks.blog_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.ApiResponse;
import tech.toshitworks.blog_app.payloads.UserDto;
import tech.toshitworks.blog_app.security.JWTTokenHelper;
import tech.toshitworks.blog_app.service.UserService;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.UserRoutes;

import java.util.List;

@RestController
@RequestMapping(UserRoutes.BASE)
public class UserController {

    private final UserService userService;
    private final JWTTokenHelper jwtTokenHelper;

    public UserController(UserService userService, JWTTokenHelper jwtTokenHelper) {
        this.userService = userService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @GetMapping(UserRoutes.GET_BY_ID)
    public ResponseEntity<UserDto> getUserById(HttpServletRequest request,@PathVariable Long id) {
        Long userId;
        if(id== 0)
            userId = extractIdFromRequest(request);
        else
            userId = id;
        return new ResponseEntity<>(userService.get(userId), HttpStatus.OK);
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
    public ResponseEntity<UserDto> updateUser(HttpServletRequest request, @Valid @RequestBody UserDto userDto) {
        Long id = extractIdFromRequest(request);
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.OK);
    }

    @DeleteMapping(UserRoutes.DELETE)
    public ResponseEntity<ApiResponse> deleteUser(HttpServletRequest request) {
        Long id = extractIdFromRequest(request);
        userService.delete(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted", true), HttpStatus.OK);
    }

    @GetMapping(UserRoutes.GET_ALL)
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    private Long extractIdFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token =  authorizationHeader.substring(7);
            return jwtTokenHelper.extractUserId(token);
        }
        return null;
    }
}
