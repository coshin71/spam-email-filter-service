package hello.spamemailfilterservice.service;

import hello.spamemailfilterservice.dto.UserDto;
import hello.spamemailfilterservice.entity.User;
import hello.spamemailfilterservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserDto userDto) {
        String rawPassword = userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(rawPassword));
        User user = userDto.toEntity();
        userRepository.save(user);
    }
}
