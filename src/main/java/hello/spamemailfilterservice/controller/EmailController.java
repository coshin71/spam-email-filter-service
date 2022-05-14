package hello.spamemailfilterservice.controller;

import hello.spamemailfilterservice.config.auth.PrincipalDetails;
import hello.spamemailfilterservice.dto.EmailDto;
import hello.spamemailfilterservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/emails/write")
    public String writeForm() {
        return "emails/writeForm";
    }

    @PostMapping("/emails/write")
    public String write(@AuthenticationPrincipal PrincipalDetails principalDetails, EmailDto emailDto) {
        emailService.writeEmail(emailDto, principalDetails.getUsername());
        return "redirect:/";
    }
}

