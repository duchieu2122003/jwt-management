package com.example.server.entity;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.StatusDepartment;
import jakarta.persistence.Cacheable;
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
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "departments")
@Cacheable
@Cache(region = "departmentsCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Departments implements Serializable {

    private static final long serialVersionUID = -3885948600652210064L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    String name;

    @Column(name = "descriptions", length = EntityProperties.LENGTH_DESCRIPTIONS)
    String descriptions;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusDepartment status;
}
