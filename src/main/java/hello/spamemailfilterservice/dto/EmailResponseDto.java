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
}
