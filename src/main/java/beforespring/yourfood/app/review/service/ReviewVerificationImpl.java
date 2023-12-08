package beforespring.yourfood.app.review.service;

import beforespring.yourfood.app.review.domain.ReviewRepository;
import beforespring.yourfood.app.review.domain.ReviewVerification;
import beforespring.yourfood.app.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewVerificationImpl implements ReviewVerification {

    private final ReviewRepository reviewRepository;

    @Override
    public Boolean isDuplicatedReview(Long reviewId) {
        return reviewRepository.existsById(reviewId);
    }
}
