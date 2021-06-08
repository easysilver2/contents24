package homework.querydsl.contents24.service;

import homework.querydsl.contents24.dto.ContentRequestDto;
import homework.querydsl.contents24.dto.ContentResponseDto;
import homework.querydsl.contents24.entity.Content;
import homework.querydsl.contents24.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository repository;

    /**
     * 컨텐츠 신규 등록
     * @param requestDto
     * @return
     */
    public Long register(ContentRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getId();
    }

    /**
     * 컨텐츠 목록 조회
     * 엔티티를 DTO로 변환해서 반환
     * @return
     */
    public List<ContentResponseDto> findAll() {
        return repository.findAll().stream()
                .map(ContentResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 컨텐츠 상세 조회
     * @param id
     * @return contentResponseDto
     */
    public ContentResponseDto detail(Long id) {
        Content content = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다. contentNo=" + id));

        return new ContentResponseDto(content);
    }

    /**
     * 컨텐츠 수정
     * @param id
     * @param requestDto
     * @return updated id
     */
    public Long update(Long id, ContentRequestDto requestDto) {
        //수정 전 조회
        Content content = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다. contentNo=" + id));

        return content.update(requestDto).getId();
    }

    /**
     * 컨텐츠 삭제
     * @param id
     * @return deleted id
     */
    public Long delete(Long id) {
        //삭제 전 조회
        Long contentId = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다. contentNo=" + id)).getId();

        repository.deleteById(contentId);

        return contentId;
    }
}
