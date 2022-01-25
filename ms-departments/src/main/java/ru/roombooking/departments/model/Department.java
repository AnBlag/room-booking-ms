package ru.roombooking.departments.model;

import lombok.*;

import static javax.persistence.GenerationType.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "name_department")
    private String nameDepartment;
    @Column(name = "position")
    private String position;

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
        Department other = (Department) obj;
        if (id == null) {
            return false;
        } else return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nameDepartment='" + nameDepartment + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}