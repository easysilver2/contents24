package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.entity.Content;
import homework.querydsl.contents24.entity.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ContentRepositoryTest {

    @Autowired ContentRepository repository;
    @Autowired PlatformRepository platformRepository;

    @Test
    void 컨텐츠_등록() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();
        platformRepository.save(platform);

        Content content = repository.save(Content.builder()
                .name("실전 Querydsl")
                .platform(platform)
                .build());

        //when
        Content findContent = repository.findById(content.getId()).get();

        //then
        assertThat(findContent.getName()).isEqualTo(content.getName());
        assertThat(findContent.getPlatform().getName()).isEqualTo(platform.getName());
        assertThat(findContent.getPlatform().getLink()).isEqualTo(platform.getLink());
    }

    @Test
    void 컨텐츠_전체조회() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();

        platformRepository.save(platform);

        Content content1 = Content.builder()
                .platform(platform)
                .name("실전! Querydsl")
                .build();

        Content content2 = Content.builder()
                .platform(platform)
                .name("실전! Spring Data JPA")
                .build();

        repository.save(content1);
        repository.save(content2);

        //when
        List<Content> contents = repository.findAll();

        //then
        assertThat(contents.size()).isEqualTo(2);
        assertThat(contents.get(0).getName()).isEqualTo("실전! Spring Data JPA");
        assertThat(contents.get(0).getPlatform().getName()).isEqualTo("인프런");
    }

    @Test
    void 컨텐츠_단건조회() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();
        platformRepository.save(platform);

        Content content = Content.builder()
                .name("실전! Querydsl")
                .platform(platform)
                .build();

        Long savedId = repository.save(content).getId();

        //when
        Content findContent = repository.findById(savedId).get();

        //then
        assertThat(findContent.getName()).isEqualTo(content.getName());
        assertThat(findContent.getPlatform().getName()).isEqualTo(platform.getName());
    }

    @Test
    void 컨텐츠_수정() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();
        platformRepository.save(platform);

        Content content = repository.save(Content.builder()
                .name("실전 Querydsl")
                .platform(platform)
                .build());
        Long savedId = repository.save(content).getId();

        //when
        content.setName("인프런222");

        //then
        Content findContent = repository.findById(savedId).get();
        assertThat(findContent.getName()).isEqualTo("인프런222");
    }

    @Test
    void 컨텐츠_삭제() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();
        platformRepository.save(platform);

        Content content = repository.save(Content.builder()
                .name("실전 Querydsl")
                .platform(platform)
                .build());
        repository.save(content);

        //when
        repository.delete(content);

        //then
        List<Content> contents = repository.findAll();
        assertThat(contents.size()).isEqualTo(0);
    }

    @Test
    void 플랫폼으로_조회() {
        //given
        Platform platform = Platform.builder()
                .name("인프런")
                .link("Inflearn.com")
                .build();
        platformRepository.save(platform);

        repository.save(Content.builder()
                .name("실전! Querydsl")
                .platform(platform)
                .build());

        repository.save(Content.builder()
                .name("실전! Spring Data JPA")
                .platform(platform)
                .build());

        //when
        List<Content> contents = repository.findByPlatform(platform);
        for (Content ct : contents) {
            System.out.println("\t\t>>>>> name = " + ct.getName());
        }

        //then
        assertThat(contents.size()).isEqualTo(2);
        assertThat(contents.get(0).getName()).isEqualTo("실전! Querydsl");

    }
}