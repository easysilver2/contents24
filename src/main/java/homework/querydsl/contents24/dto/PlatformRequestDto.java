package homework.querydsl.contents24.dto;

import homework.querydsl.contents24.entity.Platform;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
