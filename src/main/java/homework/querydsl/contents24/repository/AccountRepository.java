package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 계정 레포지토리
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
