package homework.querydsl.contents24.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Account {

    // 계정 번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    private Long id;

    // 계정 아이디
    @Column(name = "account_id", nullable = false)
    private String accountId;

    // 플랫폼 정보
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "platform_no", nullable = false)
    private Platform platform;

    // 사원 정보
    @ManyToOne(fetch = LAZY)
    @JoinColumns({
            @JoinColumn(name = "employee_no", nullable = false),
            @JoinColumn(name = "dept_no", nullable = false)
    })
    private Employee employee;

    @Builder
    public Account(String accountId, Platform platform, Employee employee) {
        this.accountId = accountId;

        if (platform != null) {
            changePlatform(platform);
        }

        if (employee != null) {
            changeEmployee(employee);
        }
    }

    public void changeEmployee(Employee employee) {
        this.employee = employee;
    }

    public void changePlatform(Platform platform) {
        this.platform = platform;
    }
}
