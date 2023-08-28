package com.wemakesoftware.NavigationSystem.repository;

import com.wemakesoftware.NavigationSystem.model.MobileStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MobileStationRepository extends JpaRepository<MobileStation, String> {

    @Modifying
    @Query("update MobileStation m set m.lastKnownX = :x, m.lastKnownY = :y where m.id = :id")
    @Transactional
    void updateCoordinates(@Param("id") String id, @Param("x") float x, @Param("y") float y);
}
