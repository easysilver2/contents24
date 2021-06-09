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
    public void checkValidation() {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("플랫폼 이름 값이 입력되지 않았습니다.");

        if(link == null || name.isEmpty())
            throw new IllegalArgumentException("플랫폼 링크 값이 입력되지 않았습니다.");
    }

}
