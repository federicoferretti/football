package com.dux.infrastructure.repository.jpa;

import com.dux.infrastructure.dao.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepositoryJpa extends JpaRepository<TeamDAO, Long> {

    @Query(value = "SELECT * FROM team t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<TeamDAO> findByNameContaining(@Param("name") String name);
}
