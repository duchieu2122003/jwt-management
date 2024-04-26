package com.example.server.core.manager.repository;

import com.example.server.entity.Missions;
import com.example.server.repositoty.MissionsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface MaMissionsRepository extends MissionsRepository {

    @Query(value = """
                    SELECT m
                     FROM Missions m
            """)
    List<Missions> getAllMissions();

    Optional<Missions> findByName(String name);

    @Query(value = """
                SELECT COUNT(*) FROM employees_missions WHERE mission_id = :idMission
            """, nativeQuery = true)
    Integer countMissionsUsed(@Param("idMission") String idMission);

}
