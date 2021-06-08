package homework.querydsl.contents24.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 플랫폼 검색 조건 파라미터 클래스
 */
@Getter @Setter
public class PlatformSearchCondition {

    private String platformName;
    private String platformLink;

}
