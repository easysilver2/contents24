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

    public static int PLATFORM_NAME_MAX_SIZE = 200;
    public static int PLATFORM_LINK_MAX_SIZE = 300;

    /* 검색 조건 유효성 검증 */
    public void checkValidation() {
        if(platformName != null && platformName.length() > PLATFORM_NAME_MAX_SIZE)
            throw new IllegalArgumentException("플랫폼명 최대 글자수를 초과하였습니다.");

        if(platformLink != null && platformLink.length() > PLATFORM_LINK_MAX_SIZE)
            throw new IllegalArgumentException("링크 최대 글자수를 초과하였습니다.");
    }

}
