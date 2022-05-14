package hello.spamemailfilterservice.service;

import hello.spamemailfilterservice.dto.EmailDto;
import hello.spamemailfilterservice.entity.Email;
import hello.spamemailfilterservice.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    @Transactional
    public void writeEmail(EmailDto emailDto, String sender) {
        Email email = emailDto.toEntity(sender);
        emailRepository.save(email);
    }
}
