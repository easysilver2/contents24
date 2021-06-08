package homework.querydsl.contents24.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;

import static homework.querydsl.contents24.entity.QPlatform.platform;

public class PlatformRepositoryImpl implements PlatformRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PlatformRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 다중 파라미터 조회
     * @param condition
     * @return
     */
    @Override
    public List<PlatformResponseDto> search(PlatformSearchCondition condition) {
        return queryFactory
                .select(Projections.bean(PlatformResponseDto.class,
                        platform.name,
                        platform.link))
                .from(platform)
                .where(nameEq(condition.getPlatformName()),
                       linkLike(condition.getPlatformLink()))
                .fetch();
    }

    /* 플랫폼명 검색 조건 추가 */
    private BooleanExpression nameEq(String platformName) {
        return platformName != null ? platform.name.eq(platformName) : null;
    }

    /* 링크 검색 조건 추가 */
    private Predicate linkLike(String platformLink) {
        return platformLink != null ? platform.link.contains(platformLink) : null;
    }
}
