package com.example.server.entity;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.StatusDepartment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    String name;

    @Column(name = "desctiptions", length = EntityProperties.LENGTH_DESCRIPTIONS)
    String desctiptions;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusDepartment status;

}
