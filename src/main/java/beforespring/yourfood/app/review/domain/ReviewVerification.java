package beforespring.yourfood.app.review.domain;

public interface ReviewVerification {
    Boolean isDuplicatedReview(Long reviewId);
}
