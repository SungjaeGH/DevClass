package com.mysite.jsb.user;

import com.mysite.jsb.question.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {

        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
        siteUser.setPassword(passwordEncoder.encode(password));

        return userRepository.save(siteUser);
    }

    public SiteUser getUser(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("[ " + username + " ] not found."));
    }
}
