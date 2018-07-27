package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@Builder
@ToString(exclude = {"sender", "recipient"})
@EqualsAndHashCode(callSuper = true, exclude = {"sender", "recipient"})
@NoArgsConstructor
@AllArgsConstructor
public class Rating extends BaseEntity {
    @Column(name = "value")
    private String value;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    private User sender;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    private User recipient;
}
