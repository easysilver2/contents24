package homework.querydsl.contents24.controller;

import homework.querydsl.contents24.dto.ContentRequestDto;
import homework.querydsl.contents24.dto.ContentResponseDto;
import homework.querydsl.contents24.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/contents")
@RestController
public class ContentController {

    private final ContentService service;

    /**
     * 컨텐츠 등록
     */
    @PostMapping("/")
    public Long register(ContentRequestDto requestDto) {
        return service.register(requestDto);
    }

    /**
     * 컨텐츠 목록 조회
     */
    @GetMapping("/")
    public List<ContentResponseDto> list() {
        return service.findAll();
    }

    /**
     * 컨텐츠 단건 상세 조회
     */
    @GetMapping("/{id}")
    public ContentResponseDto detail(@PathVariable Long id) {
        return service.detail(id);
    }

    /**
     * 컨텐츠 수정
     */
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, ContentRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    /**
     * 컨텐츠 삭제
     */
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
