package hello.spamemailfilterservice.service;

import hello.spamemailfilterservice.domain.EmailType;
import hello.spamemailfilterservice.dto.EmailRequestDto;
import hello.spamemailfilterservice.dto.EmailResponseDto;
import hello.spamemailfilterservice.entity.Email;
import hello.spamemailfilterservice.filtering.SpamDetector;
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

    private final SpamDetector spamDetector;
    private final EmailRepository emailRepository;

    @Transactional(readOnly = true)
    public Page<EmailResponseDto> listEmails(Pageable pageable, String sender) {
        Page<Email> emails = emailRepository.findByReceiver(pageable, sender);
        emails.map(email -> {
            boolean isSpam = email.getEmailType() == EmailType.SPAM;
            StringBuilder builder;
            if (isSpam) {
                builder = new StringBuilder("[SPAM] ");
            } else {
                builder = new StringBuilder("[HAM] ");
            }
            builder.append(email.getTitle());
            email.setTitle(builder.toString());
            return email;
        });
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<EmailResponseDto> emailDtos = emails.map(email ->
                new EmailResponseDto(email.getId(), email.getTitle(), email.getContent(), email.getSender(), format.format(email.getTime())));
        return emailDtos;
    }

    @Transactional(readOnly = true)
    public EmailResponseDto viewEmail(Long emailId) {
        Email email = emailRepository.findById(emailId).orElseThrow();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EmailResponseDto emailDto = new EmailResponseDto(email.getTitle(), email.getContent(), email.getSender(), format.format(email.getTime()));
        return emailDto;
    }

    @Transactional
    public void writeEmail(EmailRequestDto emailDto, String sender) {
        Email email = emailDto.toEntity(sender);
        EmailType type;
        if (spamDetector.detect(email.getContent()).isSpam()) {
            type = EmailType.SPAM;
        } else {
            type = EmailType.HAM;
        }
        email.setEmailType(type);
        emailRepository.save(email);
    }
}
