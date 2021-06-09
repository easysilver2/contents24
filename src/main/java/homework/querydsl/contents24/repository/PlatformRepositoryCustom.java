package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 플랫폼 Querydsl 인터페이스
 */
public interface PlatformRepositoryCustom {

    /* 페이징 없는 검색 조회 */
    List<PlatformResponseDto> search(PlatformSearchCondition condition);

    /* 페이징 있는 검색 조회 */
    Page<PlatformResponseDto> search(PlatformSearchCondition condition, Pageable pageable);

}
