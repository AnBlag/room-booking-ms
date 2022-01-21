package ru.roombooking.front.config.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.roombooking.front.feign.ProfileFeignClient;
import ru.roombooking.front.model.dto.ProfileDTO;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    //private final ProfileService profileService;
    private final ProfileFeignClient profileFeignClient;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //ProfileDTO profile = profileService.findByLogin(login);
        ProfileDTO profileDTO = profileFeignClient.findByLogin(login);
        return AuthUser.fromUser(profileDTO);
    }
}