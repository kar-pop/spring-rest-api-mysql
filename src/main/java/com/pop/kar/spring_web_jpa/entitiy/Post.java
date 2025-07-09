package com.pop.kar.spring_web_jpa.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "post")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Min(1)
    private Integer id;
    @NotNull
    @NotBlank
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
