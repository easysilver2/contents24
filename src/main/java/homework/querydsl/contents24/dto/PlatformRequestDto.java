package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Platform;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter @Setter
public class PlatformRequestDto {

    private String name;
    private String link;

    public PlatformRequestDto(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Platform toEntity() {
        return Platform.builder()
                .name(name)
                .link(link)
                .build();
    }

    /* 신규 등록 입력 값 유효성 검증 */
    public boolean isNotValid() {
        if (name == null || name.isEmpty()) {
            log.info("Platform name is empty.");
            return true;
        }
        if(link == null || name.isEmpty()) {
            log.info("Platform link is empty.");
            return true;
        }

        return false;
    }
}
