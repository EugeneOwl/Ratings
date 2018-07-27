package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Dto {
    private int id;
    private String value;
    private String rawSender;
    private String rawRecipient;
    private User sender;
    private User recipient;
}
