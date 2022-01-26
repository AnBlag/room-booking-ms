package ru.roombooking.history.model;

import static javax.persistence.GenerationType.*;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "vsc_room")
@NoArgsConstructor
@AllArgsConstructor
public class VscRoom {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "number_room")
    private Long numberRoom;

    @Column(name = "is_active")
    private Boolean isActive;

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
        VscRoom other = (VscRoom) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "VscRoom{" +
                "id=" + id +
                ", numberRoom=" + numberRoom +
                ", isActive=" + isActive +
                '}';
    }
}