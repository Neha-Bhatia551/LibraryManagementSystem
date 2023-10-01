package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    // Currently two roles - Librarian & Borrower
    // TODO: Check if more roles are necessary
    @Column(name="role")
    private String role;
    private List<String> borrowedBooks;
}
