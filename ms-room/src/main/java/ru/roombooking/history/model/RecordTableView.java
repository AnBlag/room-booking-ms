package ru.roombooking.history.model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "record_table_view")
@Immutable
@Builder
@Subselect("select record_table.id, record_table.email, record_table.employee_id, employee.name, employee.surname, employee.middle_name, record_table.title, record_table.start_event, record_table.end_event, record_table.is_active, vsc_room.number_room\n" +
        "from record_table inner join employee on record_table.employee_id = employee.id\n" +
        "join vsc_room  on record_table.number_room_id = vsc_room.id")
public class RecordTableView {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "name")
    private String employeeName;
    @Column(name = "surname")
    private String employeeSurname;
    @Column(name = "middle_name")
    private String employeeMiddleName;
    @Column(name = "number_room")
    private Long vcsRoomNumberRoom;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "title")
    private String title;
    @Column(name = "start_event")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startEvent;
    @Column(name = "end_event")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endEvent;

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
        RecordTableView other = (RecordTableView) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "RecordTableView{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSurname='" + employeeSurname + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", vcsRoomNumberRoom=" + vcsRoomNumberRoom +
                ", isActive=" + isActive +
                ", title='" + title + '\'' +
                ", startEvent=" + startEvent +
                ", endEvent=" + endEvent +
                '}';
    }
}