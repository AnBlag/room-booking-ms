package ru.roombooking.profile.model;

import static javax.persistence.GenerationType.*;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
@SequenceGenerator(name = "default_gen", sequenceName = "role_seq", allocationSize = 1)
public class Profile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profile other = (Profile) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", accountNonLocked=" + accountNonLocked +
                '}';
    }
}