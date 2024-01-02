package vn.whatsenglish.auth.service;

import vn.whatsenglish.auth.dto.response.UserResponse;
import vn.whatsenglish.auth.entity.User;

import java.util.List;

public interface IUserService {

    User getUserById(Integer id);

    List<UserResponse> getAllUser();
}
