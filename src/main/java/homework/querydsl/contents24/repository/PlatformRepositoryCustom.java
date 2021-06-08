package homework.querydsl.contents24.repository;

import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;

import java.util.List;

public interface PlatformRepositoryCustom {

    List<PlatformResponseDto> search(PlatformSearchCondition condition);
}
