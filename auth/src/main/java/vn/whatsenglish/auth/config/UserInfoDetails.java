package vn.whatsenglish.auth.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.whatsenglish.auth.entity.Group;
import vn.whatsenglish.auth.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {

    private final String name;
    private final String password;
    @Getter
    private Integer id;
    private final List<GrantedAuthority> authorities;

    public UserInfoDetails(User user) {
        name = user.getUsername();
        password = user.getPassword();
        id = user.getId();
        authorities = generateAuthorities(user);
    }

    private List<GrantedAuthority> generateAuthorities(User user) {
        List<Group> groups = user.getGroups();
        return groups.stream()
                .map(Group::getGroupName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
