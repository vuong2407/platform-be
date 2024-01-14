package vn.whatsenglish.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.backend.config.UserInfoDetails;
import vn.whatsenglish.backend.config.UserInfoDetailsService;
import vn.whatsenglish.backend.dto.request.LoginRequest;
import vn.whatsenglish.backend.dto.request.TokenRefreshRequest;
import vn.whatsenglish.backend.dto.response.JwtResponse;
import vn.whatsenglish.backend.dto.response.TokenRefreshResponse;
import vn.whatsenglish.backend.dto.response.UserResponse;
import vn.whatsenglish.backend.entity.RefreshToken;
import vn.whatsenglish.backend.entity.User;
import vn.whatsenglish.backend.exception.TokenRefreshException;
import vn.whatsenglish.backend.jwt.JwtService;
import vn.whatsenglish.backend.jwt.RefreshJwtTokenService;
import vn.whatsenglish.backend.service.IUserService;
import vn.whatsenglish.backend.service.impl.RefreshTokenService;
import vn.whatsenglish.domain.dto.payment.request.PaymentRequestDto;
import vn.whatsenglish.domain.dto.payment.response.PaymentResponseDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private IUserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private RefreshJwtTokenService refreshJwtTokenService;
    private RefreshTokenService refreshTokenService;
    private UserInfoDetailsService userInfoDetailsService;

    @PostMapping("/create")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(UserResponse.ofEntity(userService.createUser(user)));
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

    @PostMapping("/deduct-payment")
    public ResponseEntity<PaymentResponseDto> deductAmount(@Valid @RequestBody PaymentRequestDto request) {
        return new ResponseEntity<>(userService.deductPayment(request), HttpStatus.OK);
    }

    @PostMapping("/deduct-payment/revert")
    public ResponseEntity<?> revertDeductingPayment(@Valid @RequestBody PaymentRequestDto request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "revert deducting payment success");
        userService.revertDeductingPayment(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
