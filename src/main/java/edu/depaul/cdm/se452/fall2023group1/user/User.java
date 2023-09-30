package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long userId;
    private String firstName;
    private String lastName;
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    private String role;
    private List<String> borrowedBooks;
}
