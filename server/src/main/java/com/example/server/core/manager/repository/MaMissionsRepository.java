package com.example.server.core.manager.repository;

import com.example.server.core.manager.model.response.MaMissionsResponse;
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
                    SELECT m.id as id, m.name as name, m.descriptions as descriptions
                     FROM Missions m
            """)
    List<MaMissionsResponse> getAllMissions();

    Optional<Missions> findByName(String name);

    @Query(value = """
                SELECT COUNT(*) FROM employees_missions WHERE mission_id = :idMission
            """, nativeQuery = true)
    Integer countMissionsUsed(@Param("idMission") String idMission);

}
