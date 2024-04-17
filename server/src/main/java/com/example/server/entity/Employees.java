package com.example.server.entity;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.Set;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(name = "code", unique = true, length = EntityProperties.LENGTH_CODE)
    String code;

    @Column(name = "first_name", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    String firstName;

    @Column(name = "last_name", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    String lastName;

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL, unique = true)
    String email;

    @Column(name = "password", length = EntityProperties.LENGTH_PASSWORD)
    String password;

    @Column(name = "birthday")
    Date birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "address", length = EntityProperties.LENGTH_DESCRIPTIONS_SHORT)
    @Nationalized
    String address;

    @Column(name = "street", length = EntityProperties.LENGTH_DESCRIPTIONS_SHORT)
    @Nationalized
    String street;

    @Column(name = "city", length = EntityProperties.LENGTH_DESCRIPTIONS_SHORT)
    @Nationalized
    String city;

    @Column(name = "country", length = EntityProperties.LENGTH_DESCRIPTIONS_SHORT)
    @Nationalized
    String country;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusEmployee status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Departments departments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_missions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_id")
    )
    Set<Missions> missions;
}
