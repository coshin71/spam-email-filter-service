package hello.spamemailfilterservice.dto;

import hello.spamemailfilterservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {

    private String loginId;
    private String password;

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }
}
