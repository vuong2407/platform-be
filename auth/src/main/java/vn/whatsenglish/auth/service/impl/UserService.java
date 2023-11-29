package vn.whatsenglish.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.dto.response.UserResponse;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.exception.NoDataFoundException;
import vn.whatsenglish.auth.repository.UserRepository;
import vn.whatsenglish.auth.service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("User not found !"));
        return UserResponse.ofEntity(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserResponse::ofEntity).collect(Collectors.toList());
    }
}
