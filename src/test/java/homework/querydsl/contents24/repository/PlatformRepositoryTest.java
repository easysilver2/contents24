package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;
import homework.querydsl.contents24.entity.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PlatformRepositoryTest {

    @Autowired PlatformRepository repository;

    @Test
    void 플랫폼_등록() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();

        //when
        repository.save(platform);

        //then
        Platform findPlatform = repository.findById(platform.getId()).get();
        assertThat(platform).isEqualTo(findPlatform);
        assertThat(platform.getName()).isEqualTo(findPlatform.getName());
        assertThat(platform.getLink()).isEqualTo(findPlatform.getLink());
    }

    @Test
    void 플랫폼_전체조회() {
        //given
        Platform platform1 = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();
        repository.save(platform1);

        Platform platform2 = Platform.builder()
                .name("프로그래머스")
                .link("programmers.com")
                .build();
        repository.save(platform2);

        //when
        List<Platform> platforms = repository.findAll();

        //then
        assertThat(platforms.get(0).getName()).isEqualTo("프로그래머스");
        assertThat(platforms.get(0).getLink()).isEqualTo("programmers.com");
    }

    @Test
    void 플랫폼_단건조회() {
        //given
        Platform platform1 = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();
        Long platformOneId = repository.save(platform1).getId();

        Platform platform2 = Platform.builder()
                .name("프로그래머스")
                .link("programmers.com")
                .build();
        Long platformTwoId = repository.save(platform2).getId();

        //when
        Platform findFirst = repository.findById(platformOneId).get();
        Platform findSecond = repository.findById(platformTwoId).get();

        //then
        assertThat(findFirst.getName()).isEqualTo("인프런");
        assertThat(findSecond.getName()).isEqualTo("프로그래머스");
    }

    @Test
    void 플랫폼_수정() {
        //given
        Platform orgPlatform = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();
        repository.save(orgPlatform);

        //when
        orgPlatform.setName("인프런222");
        orgPlatform.setLink("inflearn222.com");
        Platform newPlatform = repository.findById(orgPlatform.getId()).get();

        //then
        assertThat(newPlatform.getName()).isEqualTo("인프런222");
    }

    @Test
    void 플랫폼_삭제() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build();
        repository.save(platform);

        //when
        repository.delete(platform);

        //then
        assertThat(repository.findById(platform.getId()).isEmpty()).isTrue();
    }

    @Test
    void 플랫폼_검색() {
        //given
        repository.save(Platform.builder()
                .name("인프런")
                .link("inflearn.com")
                .build());

        List<PlatformResponseDto> platforms = repository.search(new PlatformSearchCondition());
        for (PlatformResponseDto platform : platforms) {
            System.out.println("\t\t>>>>> platform.name = " + platform.getName());

        }
    }

}