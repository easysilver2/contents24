package homework.querydsl.contents24.controller;

import homework.querydsl.contents24.dto.PlatformRequestDto;
import homework.querydsl.contents24.dto.PlatformResponseDto;
import homework.querydsl.contents24.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     */
    @GetMapping("/")
    public List<PlatformResponseDto> list() {
        return service.findAll();
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
