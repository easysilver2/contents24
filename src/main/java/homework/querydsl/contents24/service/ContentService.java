package homework.querydsl.contents24.service;

import homework.querydsl.contents24.dto.ContentRequestDto;
import homework.querydsl.contents24.dto.ContentResponseDto;
import homework.querydsl.contents24.dto.ContentSearchCondition;
import homework.querydsl.contents24.entity.Account;
import homework.querydsl.contents24.entity.Content;
import homework.querydsl.contents24.entity.Platform;
import homework.querydsl.contents24.repository.AccountRepository;
import homework.querydsl.contents24.repository.ContentRepository;
import homework.querydsl.contents24.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository repository;
    private final PlatformRepository platformRepository;
    private final AccountRepository accountRepository;

    /**
     * 컨텐츠 신규 등록
     * @param requestDto
     * @return
     */
    public Long register(ContentRequestDto requestDto) {
        //플랫폼 조회
        Platform platform = platformRepository.findById(requestDto.getPlatformNo())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 플랫폼입니다. platformNo=" +
                        requestDto.getPlatformNo()));

        return repository.save(requestDto
                .toEntity()
                .changePlatform(platform)).getId();
    }

    /**
     * 컨텐츠 목록 검색 조회(페이징)
     * @param condition
     * @param pageable
     * @return
     */
    public Page<ContentResponseDto> search(ContentSearchCondition condition, Pageable pageable) {
        return repository.search(condition, pageable);
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
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 컨텐츠입니다. contentNo=" + id));

        ContentResponseDto responseDto = new ContentResponseDto(content);
        responseDto.setAccountList(repository.accountListByContent(id));

        return responseDto;
    }

    /**
     * 컨텐츠 수정
     * @param id
     * @param requestDto
     * @return updated id
     */
    public Long update(Long id, ContentRequestDto requestDto) {
        Content content = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 컨텐츠입니다. contentNo=" + id));

        return repository.save(content.update(requestDto)).getId();
    }

    /**
     * 컨텐츠 삭제
     * @param id
     * @return deleted id
     */
    public Long delete(Long id) {
        Long contentId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 컨텐츠입니다. contentNo=" + id)).getId();

        repository.deleteById(contentId);

        return contentId;
    }

    public List<ContentResponseDto> listByAccount(Long accountNo) {
        //계정 조회
        Account account = accountRepository.findById(accountNo)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계정입니다. accountNo=" + accountNo));

        return repository.listByAccount(account.getId());
    }
}
