package hello.spamemailfilterservice.controller;

import hello.spamemailfilterservice.dto.UserDto;
import hello.spamemailfilterservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signupForm")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signupForm(UserDto userDto) {
        userService.signup(userDto);
        return "redirect:/";
    }
}
