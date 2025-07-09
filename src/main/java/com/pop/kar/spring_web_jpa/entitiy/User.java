package com.pop.kar.spring_web_jpa.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Min(1)
    private Integer id;
    @NotNull
    @NotBlank
    private String login;
    private String displayName;
    @Min(1900)
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "user")
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
    List<Post> posts;
}
