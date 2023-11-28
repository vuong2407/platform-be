package vn.whatsenglish.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.auth.dto.request.LoginRequest;
import vn.whatsenglish.auth.dto.request.TokenRefreshRequest;
import vn.whatsenglish.auth.dto.response.JwtResponse;
import vn.whatsenglish.auth.dto.response.TokenRefreshResponse;
import vn.whatsenglish.auth.entity.RefreshToken;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.exception.TokenRefreshException;
import vn.whatsenglish.auth.jwt.JwtService;
import vn.whatsenglish.auth.jwt.RefreshJwtTokenService;
import vn.whatsenglish.auth.jwt.UserInfoDetails;
import vn.whatsenglish.auth.jwt.UserInfoDetailsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshJwtTokenService refreshJwtTokenService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User user) {
        return userInfoDetailsService.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Admin Profile";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
                System.out.println("true");
                UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtService.generateToken(loginRequest.getUsername());
                RefreshToken refreshToken = refreshJwtTokenService.createRefreshToken(userInfoDetails.getId());
                return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", refreshToken.getToken()));
        } else {
            System.out.println("false");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login fail");
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshJwtTokenService.findByToken(requestRefreshToken)
                .map(refreshJwtTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user.getName());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken, "Bearer"));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        Object d = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoDetails userDetails = (UserInfoDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getId();
        refreshJwtTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("Log out successful!");
    }

}
