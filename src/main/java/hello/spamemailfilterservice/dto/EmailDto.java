package hello.spamemailfilterservice.dto;

import hello.spamemailfilterservice.domain.EmailType;
import hello.spamemailfilterservice.entity.Email;
import lombok.Data;

@Data
public class EmailDto {

    private String title;

    private String content;

    private String receiver;

    public Email toEntity(String sender) {
        return Email.builder()
                .title(title)
                .content(content)
                .sender(sender)
                .receiver(receiver)
                .emailType(EmailType.HAM)
                .build();
    }
}
