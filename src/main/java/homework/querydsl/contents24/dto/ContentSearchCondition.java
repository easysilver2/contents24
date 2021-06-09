package homework.querydsl.contents24.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContentSearchCondition {
    private String platformName;
    private String contentName;
    private String accountId;

    public static final int PLATFORM_NAME_MAX_SIZE = 200;
    public static final int CONTENT_NAME_MAX_SIZE = 200;

    public void checkValidation() {
        if (platformName != null && platformName.length() > PLATFORM_NAME_MAX_SIZE)
            throw new IllegalArgumentException("플랫폼명 최대 글자수를 초과하였습니다.");

        if(contentName != null && contentName.length() > CONTENT_NAME_MAX_SIZE)
            throw new IllegalArgumentException("컨텐츠명 최대 글자수를 초과하였습니다.");
    }
}
