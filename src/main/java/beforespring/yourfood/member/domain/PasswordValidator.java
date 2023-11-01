package beforespring.yourfood.member.domain;

public interface PasswordValidator {

    void validate(AuthProfile authProfile, String rawPassword, PasswordHasher passwordHasher);
}
