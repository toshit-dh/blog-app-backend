package tech.toshitworks.blog_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    private void authenticateToken(String username,String password){
        UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(uPAT);
    }
}
