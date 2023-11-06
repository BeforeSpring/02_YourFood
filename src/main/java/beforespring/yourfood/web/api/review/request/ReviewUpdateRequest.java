package beforespring.yourfood.web.api.review.request;

import lombok.Builder;

public record ReviewUpdateRequest(Long reviewId, int rating, String content) {
    @Builder
    public ReviewUpdateRequest {
    }
}
