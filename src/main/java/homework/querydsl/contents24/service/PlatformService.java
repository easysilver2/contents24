package homework.querydsl.contents24.service;

import homework.querydsl.contents24.dto.PlatformRequestDto;
import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.entity.Platform;
import homework.querydsl.contents24.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlatformService {

    private final PlatformRepository repository;

    /**
     * 플랫폼 신규 등록
     * @param requestDto
     * @return
     */
    public Long register(PlatformRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getId();
    }

    /**
     * 플랫폼 목록 조회
     * TODO : 페이징 처리 적용 필요
     * TODO : 검색 조건 적용 필요
     * 조회해 온 엔티티를 DTO로 변환하여 리스트 반환
     */
    public List<PlatformResponseDto> findAll() {
        return repository.findAll().stream()
                .map(PlatformResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 플랫폼 단건 상세 조회
     * @param id
     * @return responseDto
     */
    public PlatformResponseDto detail(Long id) {
        // 플랫폼 정보 조회
        Platform platform = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플랫폼입니다. platformNo=" + id));

        // TODO : 해당 플랫폼 소속 전체 컨텐츠 목록 조회

        // TODO : 해당 플랫폼을 이용하는 계정 및 사원 정보 목록 조회

        return new PlatformResponseDto(platform);
    }

    /**
     * 플랫폼 수정
     * @param id
     * @param requestDto
     * @return id
     */
    public Long update(Long id, PlatformRequestDto requestDto) {
        // 수정 전 조회(영속 상태)
        Platform platform = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플랫폼입니다. platformNo=" + id));

        if(requestDto != null)
            platform.update(requestDto);

        return platform.getId();
    }

    /**
     * 플랫폼 단건 삭제(PK)
     * @param id
     * @return id
     */
    public Long deleteById(Long id) {
        // 삭제 전 조회
        Long platformNo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플랫폼입니다. platformNo=" + id)).getId();

        // 삭제
        repository.deleteById(platformNo);

        return platformNo;
    }
}
