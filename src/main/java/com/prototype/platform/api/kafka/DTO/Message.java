package com.prototype.platform.api.kafka.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String type;
    private String date;
    private String message;
}
