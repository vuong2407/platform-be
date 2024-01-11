package vn.whatsenglish.auth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.config.EncodeConfig;
import vn.whatsenglish.auth.dto.response.UserResponse;
import vn.whatsenglish.auth.entity.Group;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.enums.Groups;
import vn.whatsenglish.auth.exception.NoDataFoundException;
import vn.whatsenglish.auth.repository.UserRepository;
import vn.whatsenglish.auth.service.IGroupService;
import vn.whatsenglish.auth.service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
    private EncodeConfig encodeConfig;
    private IGroupService groupService;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("User not found !"));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encodeConfig.passwordEncoder().encode(user.getPassword()));
        Group group = groupService.findGroupByCode(Groups.CUSTOMER.getGroupCode());
        if (user.getGroups().isEmpty()) user.getGroups().add(group);
        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserResponse::ofEntity).collect(Collectors.toList());
    }
}
