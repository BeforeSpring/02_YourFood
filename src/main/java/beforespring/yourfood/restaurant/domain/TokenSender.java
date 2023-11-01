package beforespring.yourfood.restaurant.domain;

public interface TokenSender {
    void sendEmail(String email, String token);

    String generateToken();
}
