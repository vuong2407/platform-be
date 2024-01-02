package vn.whatsenglish.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.auth.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private int id;
    private String username;
    private String email;
    private String roles;

    public static UserResponse ofEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
