package hello.spamemailfilterservice.dto;

import hello.spamemailfilterservice.entity.User;
import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
