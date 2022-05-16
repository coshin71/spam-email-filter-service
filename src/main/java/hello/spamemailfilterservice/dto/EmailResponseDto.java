package hello.spamemailfilterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailResponseDto {

    private Long id;

    private String title;

    private String content;

    private String sender;

    private String time;

    public EmailResponseDto(String title, String content, String sender, String time) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.time = time;
    }
}
