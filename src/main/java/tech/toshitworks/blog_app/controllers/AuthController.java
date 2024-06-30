package tech.toshitworks.blog_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tech.toshitworks.blog_app.payloads.JWTRequest;
import tech.toshitworks.blog_app.payloads.JWTResponse;
import tech.toshitworks.blog_app.security.JWTTokenHelper;
import tech.toshitworks.blog_app.utils.Constants.ApiRoutes.AuthRoutes;

@RestController
@RequestMapping(AuthRoutes.BASE)
public class AuthController {

    private final JWTTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JWTTokenHelper jwtTokenHelper, UserDetailsService userDetailsService,AuthenticationManager authenticationManager) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(AuthRoutes.LOGIN)
    public ResponseEntity<JWTResponse> createToken(@RequestBody JWTRequest jwtRequest){
        authenticateToken(jwtRequest.getEmail(),jwtRequest.getPassword());
        String token = jwtTokenHelper.generateToken(userDetailsService.loadUserByUsername(jwtRequest.getEmail()));
        return new ResponseEntity<>(new JWTResponse(token), HttpStatus.OK);
    }

    @GetMapping(AuthRoutes.VERIFY)
    public ResponseEntity<Boolean> verifyToken(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token =  authorizationHeader.substring(7);
            String username = jwtTokenHelper.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new ResponseEntity<>(jwtTokenHelper.validateToken(token,userDetails),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticateToken(String username,String password){
        UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(uPAT);
    }
}
