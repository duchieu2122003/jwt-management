package com.example.server.entity;

import com.example.server.infrastructure.constant.EntityProperties;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@ToString
@Getter
@Table(name = "missions")
@Cacheable
@Cache(region = "missionsCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    String name;

    @Column(name = "descriptions", length = EntityProperties.LENGTH_DESCRIPTIONS)
    String descriptions;

    @Column(name = "salary")
    Integer salary;
}
