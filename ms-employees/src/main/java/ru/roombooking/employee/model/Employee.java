package ru.roombooking.employee.model;

import static javax.persistence.GenerationType.*;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone")
    private String phone;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "profile_id")
    private Long profileId;

    @NotNull
    @Column(name = "department_id")
    private Long departmentId;

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
        Employee other = (Employee) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}