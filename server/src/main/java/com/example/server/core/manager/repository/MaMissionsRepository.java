package com.example.server.core.manager.repository;

import com.example.server.core.manager.model.response.MaMissionsResponse;
import com.example.server.entity.Missions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duchieu212
 */
@Repository
public interface MaMissionsRepository extends JpaRepository<Missions, String> {

    @Query(value = """
                    SELECT m.id as id, m.name as name
                     FROM Missions m
            """)
    List<MaMissionsResponse> getAllMissions();
}
