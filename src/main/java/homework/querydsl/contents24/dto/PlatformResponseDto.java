package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Platform;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlatformResponseDto {
    private Long id;
    private String name;
    private String link;

    public PlatformResponseDto(Platform entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.link = entity.getLink();
    }
}
