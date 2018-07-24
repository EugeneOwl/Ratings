package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rating extends BaseEntity {
    @Column(name = "value")
    private String value;

    @Transient
    private String rawSender;

    @Transient
    private String rawRecipient;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private User sender;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private User recipient;
}
