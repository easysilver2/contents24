package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ContentRequestDto {

    private String name;

    public ContentRequestDto(String name) {
        this.name = name;
    }

    public Content toEntity() {
        return Content.builder()
                .name(name)
                .build();
    }

    /* 입력 값 유효성 검증 */
    public void checkValidation() {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("컨텐츠명 입력 값이 없습니다.");
    }
}
