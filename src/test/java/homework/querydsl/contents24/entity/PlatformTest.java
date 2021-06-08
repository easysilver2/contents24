package homework.querydsl.contents24.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static homework.querydsl.contents24.entity.QPlatform.platform;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PlatformTest {

    @PersistenceContext EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Platform platform1 = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();

        Platform platform2 = Platform.builder()
                .name("유튜브")
                .link("youtube.com")
                .build();

        em.persist(platform1);
        em.persist(platform2);
    }

    @Test
    public void 플랫폼_검색() {
        Platform findPlatform = queryFactory
                .select(platform)
                .from(platform)
                .where(platform.name.eq("인프런"))
                .fetchOne();

        assertThat(findPlatform.getName()).isEqualTo("인프런");
    }

    @Test
    void 플랫폼_조건_검색() {
        Platform findPlatform = queryFactory
                .selectFrom(QPlatform.platform)
                .where(QPlatform.platform.name.eq("유튜브")
                        .and(QPlatform.platform.link.contains("youtube")))
                .fetchOne();

        assertThat(findPlatform.getLink()).isEqualTo("youtube.com");
        assertThat(findPlatform.getName()).isEqualTo("유튜브");
    }


    @Test
    public void testJPQL() {
        // 영속성 컨텍스트 초기화
        em.flush();
        em.clear();

        List<Platform> platforms = em.createQuery("SELECT p FROM Platform p", Platform.class)
                .getResultList();

        for (Platform pf : platforms) {
            System.out.println("platform.name = " + pf.getName());
            System.out.println("platform.link = " + pf.getLink());
            System.out.println();
        }

        assertThat(platforms.size()).isEqualTo(2);
        assertThat(platforms.get(0).getName()).isEqualTo("인프런");
    }

}