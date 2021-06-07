package homework.querydsl.contents24.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PlatformTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Platform platform1 = Platform.builder()
                .name("Inflearn")
                .link("Inflearn.com")
                .build();

        Platform platform2 = Platform.builder()
                .name("Youtube")
                .link("Youtube.com")
                .build();

        em.persist(platform1);
        em.persist(platform2);

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
        assertThat(platforms.get(0).getName()).isEqualTo("Inflearn");
    }

}