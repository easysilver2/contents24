package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ContentResponseDto {

    private Long id;
    private String name;

    public ContentResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ContentResponseDto(Content entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
