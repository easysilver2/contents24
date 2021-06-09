package homework.querydsl.contents24.controller;

import homework.querydsl.contents24.dto.PlatformRequestDto;
import homework.querydsl.contents24.dto.PlatformSearchCondition;
import homework.querydsl.contents24.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 플랫폼 컨트롤러
 */
@RequiredArgsConstructor
@RequestMapping("/platforms")
@RestController
public class PlatformController {

    private final PlatformService service;

    /**
     * 신규 등록
     * @param requestDto
     * @return
     */
    @PostMapping("/")
    public ResponseEntity register(PlatformRequestDto requestDto) {
        requestDto.checkValidation();
        return new ResponseEntity(service.register(requestDto), HttpStatus.CREATED);
    }

    /**
     * 목록 조회(검색 조건: 플랫폼 이름, 플랫폼 링크 | 페이징 처리)
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping("/")
    public ResponseEntity list(PlatformSearchCondition condition, Pageable pageable) {
        condition.checkValidation();
        return new ResponseEntity<>(service.search(condition, pageable), HttpStatus.OK);
    }

    /**
     * 상세
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        return new ResponseEntity(service.detail(id), HttpStatus.OK);
    }

    /**
     * 수정
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, PlatformRequestDto requestDto) {
        requestDto.checkValidation();
        return new ResponseEntity(service.update(id, requestDto), HttpStatus.OK);
    }

    /**
     * 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return new ResponseEntity(service.deleteById(id), HttpStatus.OK);
    }
}
