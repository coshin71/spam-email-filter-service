package hello.spamemailfilterservice.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    private String loginId;
    private String password;

    public User() {
    }
}

