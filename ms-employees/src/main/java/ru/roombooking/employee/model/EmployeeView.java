package ru.roombooking.employee.model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_view")
@Immutable
@Builder
@Subselect("select profile.id, employee.name, employee.surname, employee.middle_name, employee.phone," +
        " employee.email, profile.account_non_locked as banned from employee inner join profile" +
        " on employee.profile_id = profile.id")
public class EmployeeView {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "banned")
    private Boolean banned;

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
        EmployeeView other = (EmployeeView) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "ProfileView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", banned=" + banned +
                '}';
    }
}