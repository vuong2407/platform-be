package vn.whatsenglish.backend.service;

import vn.whatsenglish.backend.dto.response.UserResponse;
import vn.whatsenglish.backend.entity.User;

import java.util.List;

public interface IUserService {

    User getUserById(Integer id);
    User createUser(User user);
    List<UserResponse> getAllUser();
}
