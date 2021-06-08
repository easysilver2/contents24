package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Content;
import lombok.Getter;

@Getter
public class ContentResponseDto {

    private Long id;
    private String name;

    public ContentResponseDto(Content entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
