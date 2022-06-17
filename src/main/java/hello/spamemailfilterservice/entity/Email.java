package hello.spamemailfilterservice.entity;

import hello.spamemailfilterservice.domain.EmailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    @GeneratedValue
    @Column(name = "EMAIL_ID")
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    private String sender;

    private String receiver;

    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    @CreationTimestamp
    private Timestamp time;
}
