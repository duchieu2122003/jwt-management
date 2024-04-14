package com.example.server.entity;

import com.example.server.infrastructure.constant.EntityProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@ToString
@Getter
@Table(name = "missions")
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    String name;

    @Column(name = "descriptions", length = EntityProperties.LENGTH_DESCRIPTIONS)
    String descriptions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employees_missions",
            joinColumns = @JoinColumn(name = "missions_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_id")
    )
    Set<Employees> employees;

}
