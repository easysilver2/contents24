package homework.querydsl.contents24.controller;

import homework.querydsl.contents24.dto.ContentRequestDto;
import homework.querydsl.contents24.dto.ContentSearchCondition;
import homework.querydsl.contents24.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 컨텐츠 컨트롤러
 */
@RequiredArgsConstructor
@RequestMapping("/contents")
@RestController
public class ContentController {

    private final ContentService service;

    /**
     * 신규 등록
     * @param requestDto
     * @return created Id
     */
    @PostMapping("/")
    public ResponseEntity register(ContentRequestDto requestDto) {
        requestDto.checkValidation();
        return new ResponseEntity(service.register(requestDto), HttpStatus.CREATED);
    }

    /**
     * 목록 조회(페이징| 검색 조건: 컨텐츠명, 플랫폼명)
     * @param condition
     * @param pageable
     * @return Page<ContentResponseDto>
     */
    @GetMapping("/")
    public ResponseEntity list(ContentSearchCondition condition, Pageable pageable) {
        condition.checkValidation();
        return new ResponseEntity(service.search(condition, pageable), HttpStatus.OK);
    }

    /**
     * 상세 조회
     * @param id
     * @return ContentsResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        return new ResponseEntity(service.detail(id), HttpStatus.OK);
    }

    /**
     * 수정
     * @param id
     * @param requestDto
     * @return updated Id
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, ContentRequestDto requestDto) {
        requestDto.checkValidation();
        return new ResponseEntity(service.update(id, requestDto), HttpStatus.OK);
    }

    /**
     * 삭제
     * @param id
     * @return deleted Id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return new ResponseEntity(service.delete(id), HttpStatus.OK);
    }
}
