package beforespring.yourfood.app.restaurant.infra;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class CuisineTypeConverterTest {
    CuisineTypeConverter cuisineTypeConverter = new CuisineTypeConverter();
    Set<CuisineType> cuisineTypeList = new TreeSet<>();

    @Test
    @DisplayName("CuisineType이 정렬되어 문자열에 저장되어야 함")
    void convert_cuisine_type_to_string_test() {
        //given
        cuisineTypeList.add(CuisineType.KOREAN);
        cuisineTypeList.add(CuisineType.WESTERN);
        cuisineTypeList.add(CuisineType.JAPANESE);
        cuisineTypeList.add(CuisineType.CHINESE);

        //when
        String dbData = cuisineTypeConverter.convertToDatabaseColumn(cuisineTypeList);

        //then
        assertThat(dbData).isEqualTo("CHINESE JAPANESE KOREAN WESTERN");
    }

    @Test
    @DisplayName("중복되는 값은 제거하고 CuisineType으로 변환되어야 함")
    void convert_string_to_cuisine_type_test() {
        //given
        String dbData = "JAPANESE KOREAN JAPANESE";

        //when
        Set<CuisineType> attribute = cuisineTypeConverter.convertToEntityAttribute(dbData);

        //then
        assertThat(attribute.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("변환된 CuisineType은 enum 순으로 정렬되어야 함")
    void convert_string_to_cuisine_type_sorted_test() {
        //given
        String dbData = "JAPANESE KOREAN JAPANESE CHINESE";
        Set<CuisineType> cuisineTypes = new TreeSet<>();
        cuisineTypes.add(CuisineType.KOREAN);
        cuisineTypes.add(CuisineType.CHINESE);
        cuisineTypes.add(CuisineType.JAPANESE);

        //when
        Set<CuisineType> attribute = cuisineTypeConverter.convertToEntityAttribute(dbData);

        //then
        assertThat(attribute).isEqualTo(cuisineTypes);
    }
}