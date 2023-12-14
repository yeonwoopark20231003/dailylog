package com.sparta.dailylog.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@Table (name ="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String userId;

    @Column (nullable = false)
    private String password;

    public User(String user_id, String password) {
        this.userId = user_id;
        this.password = password;
    }
}
