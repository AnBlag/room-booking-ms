package ru.roombooking.history.model;

import lombok.*;

import static javax.persistence.GenerationType.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "history_record_table_employee")
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRecordTableEmployee {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
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

    private Long numberRoomId;

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
        HistoryRecordTableEmployee other = (HistoryRecordTableEmployee) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "HistoryRecordTableEmployee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", startEvent=" + startEvent +
                ", endEvent=" + endEvent +
                ", isActive=" + isActive +
                ", numberRoomId=" + numberRoomId +
                ", employeeId=" + employeeId +
                '}';
    }
}