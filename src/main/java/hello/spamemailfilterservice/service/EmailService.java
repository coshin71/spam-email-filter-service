package hello.spamemailfilterservice.service;

import hello.spamemailfilterservice.dto.EmailRequestDto;
import hello.spamemailfilterservice.dto.EmailResponseDto;
import hello.spamemailfilterservice.entity.Email;
import hello.spamemailfilterservice.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    @Transactional(readOnly = true)
    public Page<EmailResponseDto> listEmails(Pageable pageable, String sender) {
        Page<Email> emails = emailRepository.findByReceiver(pageable, sender);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<EmailResponseDto> emailDtos = emails.map(email ->
                new EmailResponseDto(email.getId(), email.getTitle(), email.getContent(), email.getSender(), format.format(email.getTime())));
        return emailDtos;
    }

    @Transactional
    public void writeEmail(EmailRequestDto emailDto, String sender) {
        Email email = emailDto.toEntity(sender);
        emailRepository.save(email);
    }
}
