package homework.querydsl.contents24.controller;

import homework.querydsl.contents24.dto.PlatformRequestDto;
import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;
import homework.querydsl.contents24.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/platforms")
@RestController
public class PlatformController {

    private final PlatformService service;

    /**
     * 신규 등록
     */
    @PostMapping("/")
    public Long register(PlatformRequestDto requestDto) {
        return service.register(requestDto);
    }

    /**
     * 목록 조회
     * 검색 조건(플랫폼명, 플랫폼 링크)
     * @return
     */
    @GetMapping("/")
    public Page<PlatformResponseDto> list(PlatformSearchCondition condition, Pageable pageable) {
        return service.search(condition, pageable);
    }

    /**
     * 상세
     */
    @GetMapping("/{id}")
    public PlatformResponseDto detail(@PathVariable Long id) {
        return service.detail(id);
    }

    /**
     * 수정
     */
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, PlatformRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    /**
     * 삭제
     */
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
