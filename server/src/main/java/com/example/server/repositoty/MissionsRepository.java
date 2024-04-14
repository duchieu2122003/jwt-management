package com.example.server.repositoty;

import com.example.server.entity.Missions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duchieu212
 */
@Repository
public interface MissionsRepository extends JpaRepository<Missions, String> {
}
