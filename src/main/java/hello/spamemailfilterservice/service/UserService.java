package hello.spamemailfilterservice.service;

import hello.spamemailfilterservice.dto.UserDto;
import hello.spamemailfilterservice.model.User;
import hello.spamemailfilterservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(UserDto userDto) {
        User user = userDto.toEntity();
        userRepository.save(user);
    }
}
