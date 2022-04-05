package ru.roombooking.front.config.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.roombooking.front.model.dto.ProfileDTO;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean active;
    private final boolean accountNonLocked;


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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public static UserDetails fromUser(ProfileDTO profile) {
        return new org.springframework.security.core.userdetails.User(
                profile.getLogin(), profile.getPassword(),
                profile.getIsActive(),
                true,
                true,
                profile.getAccountNonLocked(),
                profile.getRole().getAuthorities()
        );
    }
}