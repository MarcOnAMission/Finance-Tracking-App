package com.MarcOnAMission.finance_tracker.Model;

import jakarta.persistence.*;
import lombok.*;

@Setter@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationUserId;
    private String username;
    private String password;
    public ApplicationUser(String passedLoginUsername, String passedLoginPassword){
        this.username = passedLoginUsername;
        this.password = passedLoginPassword;
    }
}
