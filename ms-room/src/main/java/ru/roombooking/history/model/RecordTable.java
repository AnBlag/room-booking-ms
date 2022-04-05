package ru.roombooking.history.model;

import static javax.persistence.GenerationType.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "record_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordTable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "start_event")
    private ZonedDateTime startEvent;

    @Column(name = "end_event")
    private ZonedDateTime endEvent;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "number_room_id")
    private Long numberRoomId;

    @Column(name = "employee_id")
    private Long employeeId;

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
        RecordTable other = (RecordTable) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "RecordTable{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", startEvent=" + startEvent +
                ", endEvent=" + endEvent +
                ", isActive=" + isActive +
                '}';
    }
}