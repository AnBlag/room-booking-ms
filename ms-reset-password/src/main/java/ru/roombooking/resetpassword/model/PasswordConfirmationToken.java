package ru.roombooking.resetpassword.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password_confirmation_token")
public class PasswordConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    @Column(name = "token")
    private String token;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profileId;


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
        PasswordConfirmationToken other = (PasswordConfirmationToken) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "PasswordConfirmationToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            '}';
    }
}
