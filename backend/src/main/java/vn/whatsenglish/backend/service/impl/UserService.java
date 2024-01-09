package vn.whatsenglish.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.whatsenglish.backend.config.EncodeConfig;
import vn.whatsenglish.backend.dto.response.UserResponse;
import vn.whatsenglish.backend.entity.Group;
import vn.whatsenglish.backend.entity.User;
import vn.whatsenglish.backend.enums.Groups;
import vn.whatsenglish.backend.exception.NoDataFoundException;
import vn.whatsenglish.backend.repository.UserRepository;
import vn.whatsenglish.backend.service.IGroupService;
import vn.whatsenglish.backend.service.IUserService;

import java.util.List;
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
