package vn.whatsenglish.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.auth.dto.request.LoginRequest;
import vn.whatsenglish.auth.dto.request.TokenRefreshRequest;
import vn.whatsenglish.auth.dto.response.JwtResponse;
import vn.whatsenglish.auth.dto.response.TokenRefreshResponse;
import vn.whatsenglish.auth.dto.response.UserResponse;
import vn.whatsenglish.auth.entity.RefreshToken;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.exception.TokenRefreshException;
import vn.whatsenglish.auth.jwt.JwtService;
import vn.whatsenglish.auth.jwt.RefreshJwtTokenService;
import vn.whatsenglish.auth.config.UserInfoDetails;
import vn.whatsenglish.auth.config.UserInfoDetailsService;
import vn.whatsenglish.auth.service.IUserService;
import vn.whatsenglish.auth.service.impl.RefreshTokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

    private UserInfoDetailsService userInfoDetailsService;
    private IUserService userService;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    private RefreshJwtTokenService refreshJwtTokenService;

    private RefreshTokenService refreshTokenService;

    @PostMapping("/addNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(UserResponse.ofEntity(userService.createUser(user)));
    }

    @GetMapping("/user/userProfile")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public String userProfile() {
        return "User Profile";
    }

    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(loginRequest.getUsername());
        RefreshToken refreshToken = refreshJwtTokenService.createOrUpdateRefreshToken(userInfoDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", refreshToken.getToken()));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        try {
            return refreshTokenService.findByToken(requestRefreshToken)
                    .map(refreshJwtTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        String token = jwtService.generateToken(user.getUsername());
                        return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken, "Bearer"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                            "Refresh token is not in database!"));
        } catch (TokenRefreshException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(UserResponse.ofEntity(userService.getUserById(id)));
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

}
