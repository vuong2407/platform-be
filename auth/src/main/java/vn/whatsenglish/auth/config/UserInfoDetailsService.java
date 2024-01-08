package vn.whatsenglish.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.entity.User;
import vn.whatsenglish.auth.exception.NoDataFoundException;
import vn.whatsenglish.auth.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByUsername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new NoDataFoundException("User not found " + username));
    }

}
