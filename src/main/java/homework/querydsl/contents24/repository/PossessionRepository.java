package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.entity.Possession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PossessionRepository extends JpaRepository<Possession, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Possession p WHERE p.account.id IN :ids")
    void deleteAllByAccount(@Param("ids") List<Long> accountIds);

    @Modifying
    @Transactional
    @Query("DELETE FROM Possession p WHERE p.content.id = :id")
    void deleteAllByContent(@Param("id") Long contentNo);
}
