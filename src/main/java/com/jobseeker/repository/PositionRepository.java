package com.jobseeker.repository;

import com.jobseeker.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p.url FROM Position p WHERE LOWER(p.name) LIKE LOWER(concat('%', concat(:keyword, '%'))) " +
           "AND LOWER(p.location) LIKE LOWER(concat('%', concat(:location, '%')))")
    List<String> findPositions(@Param("keyword") String keyword, @Param("location") String location);
}
