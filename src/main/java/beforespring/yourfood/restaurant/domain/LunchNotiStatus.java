package beforespring.yourfood.restaurant.domain;

public enum LunchNotiStatus {
    AGREE,
    DISAGREE;

    /**
     *
     * @return 소문자로 반환 (ex: KOREAN -> korean)
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
