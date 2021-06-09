package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.entity.Content;
import homework.querydsl.contents24.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 컨텐츠 레포지토리
 */
public interface ContentRepository extends JpaRepository<Content, Long>, ContentRepositoryCustom {

    @Query("SELECT c FROM Content c ORDER BY c.id DESC")
    List<Content> findAll();

    List<Content> findByPlatform(Platform platform);
}
