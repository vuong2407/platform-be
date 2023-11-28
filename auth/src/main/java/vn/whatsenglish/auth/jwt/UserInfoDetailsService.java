package vn.whatsenglish.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.config.EncodeConfig;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.exception.NoDataFoundException;
import vn.whatsenglish.auth.repository.UserRepository;

import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncodeConfig encodeConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new NoDataFoundException("User not found " + username));
    }

    public String addUser(User user) {
        user.setPassword(encodeConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }

}
