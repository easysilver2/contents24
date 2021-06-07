package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("SELECT p FROM Platform p ORDER BY p.id DESC")
    List<Platform> findAll();
}
