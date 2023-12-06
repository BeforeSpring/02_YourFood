package beforespring.yourfood.web.api.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public record GenericResponse<T>(
    int statusCode,
    T data,
    String message) {

    @Builder
    public GenericResponse {
    }

    public static <T> GenericResponse<T> ok(T data) {
        return new GenericResponse<>(HttpStatus.OK.value(), data, HttpStatus.OK.getReasonPhrase());
    }
}
