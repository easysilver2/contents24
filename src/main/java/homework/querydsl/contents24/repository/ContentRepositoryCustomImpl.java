package homework.querydsl.contents24.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.querydsl.contents24.dto.ContentResponseDto;
import homework.querydsl.contents24.dto.ContentSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static homework.querydsl.contents24.entity.QContent.content;
import static homework.querydsl.contents24.entity.QPlatform.platform;

public class ContentRepositoryCustomImpl implements ContentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ContentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 다중 파라미터 조회(플랫폼명, 컨텐츠명)
     * @param condition
     * @return
     */
    @Override
    public List<ContentResponseDto> search(ContentSearchCondition condition) {
        return queryFactory
                .select(Projections.bean(ContentResponseDto.class,
                        content.id,
                        content.name))
                .from(content)
                //플랫폼 엔티티와 조인
                .leftJoin(content.platform, platform)
                .where(
                        platformNameEq(condition.getPlatformName()),
                        contentNameEq(condition.getContentName()))
                .fetch();
    }

    /**
     * 다중 파라미터 조회(페이징 포함)
     * @param condition
     * @param pageable
     * @return
     */
    @Override
    public Page<ContentResponseDto> search(ContentSearchCondition condition, Pageable pageable) {
        QueryResults<ContentResponseDto> results = queryFactory
                .select(Projections.bean(ContentResponseDto.class,
                        content.id,
                        content.name))
                .from(content)
                //플랫폼 엔티티와 조인
                .leftJoin(content.platform, platform)
                .where(platformNameEq(condition.getPlatformName()),
                        contentNameEq(condition.getContentName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ContentResponseDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    /* 플랫폼명 검색 조건 추가 */
    private BooleanExpression platformNameEq(String platformName) {
        return platformName != null ? content.platform.name.eq(platformName) : null;
    }

    /* 컨텐츠명 검색 조건 추가 */
    private BooleanExpression contentNameEq(String contentName) {
        return contentName != null ? content.name.eq(contentName) : null;
    }
}
