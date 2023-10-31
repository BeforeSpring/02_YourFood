package beforespring.yourfood.restaurant.domain;

public enum CuisineType {
    KOREAN,
    CHINESE,
    JAPANESE,
    WESTERN;

    /**
     * @return 소문자로 반환 (ex: KOREAN -> korean)
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
