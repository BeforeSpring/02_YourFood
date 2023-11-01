package beforespring.yourfood.member.domain;

import beforespring.yourfood.member.exception.PasswordMismatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
    name = "auth_profile",
    indexes = {
        @Index(
            name = "idx__auth_profile__username",
            columnList = "username",
            unique = true
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthProfile {

    @Id
    @Column(name = "auth_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean confirmStatus;

    @Builder
    protected AuthProfile(
        Long id,
        String username,
        String raw,
        PasswordHasher hasher
    ) {
        this.id = id;
        this.username = username;
        this.password = hasher.hash(raw);
        this.confirmStatus = false;
    }

    /**
     * 입력받은 rawPassword를 Hasher를 통해 변환했을 때,
     * 저장된 password와 같은 지 확인하는 메서드
     *
     * @param rawPassword
     * @param hasher
     */
    public void verifyPassword(String rawPassword, PasswordHasher hasher) {
        if (!hasher.isMatch(rawPassword, password)) {
            throw new PasswordMismatchException();
        }
    }

    /**
     * 입력받은 비밀번호의 유효성을 검사한 후에
     * 올바르면 업데이트하는 메서드
     *
     * @param rawPassword
     * @param hasher
     */
    public void updatePassword(String rawPassword, PasswordValidator validator, PasswordHasher hasher) {
        validator.validate(this, rawPassword, hasher);
        this.password = hasher.hash(rawPassword);
    }

    /**
     * 가입 승인 시 상태를 승인으로 변경
     */
    public void joinConfirm() {
        this.confirmStatus = true;
    }
}

