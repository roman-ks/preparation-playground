package com.mashkario.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Profile("jwt")
@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

//     @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // GET /user endpoint
//    @GetMapping("/user")
//    public String user(@AuthenticationPrincipal OAuth2ResourceServerProperties.Jwt principal) {
//        return "user";
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception {
//        log.info("authRequest: {}", authRequest);
        final UserDetails userDetails = new User(authRequest.getUsername(), authRequest.getPassword(), new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @GetMapping("/claims")
//    public ResponseEntity<?> getClaims(@RequestHeader("Authorization") String token) {
//        String jwtToken = token.substring(7);
//        Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
//        return ResponseEntity.ok(claims);
//    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (UsernameNotFoundException e) {
//            throw new Exception("USER_NOT_FOUND", e);
//        }
//    }
}
