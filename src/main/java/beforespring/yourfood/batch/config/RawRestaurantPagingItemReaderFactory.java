package beforespring.yourfood.batch.config;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.batch.rawrestaurant.infra.OpenApiManagerFactory;
import beforespring.yourfood.batch.rawrestaurant.infra.fetcherimplement.GyeonggiFetcherImpl;
import beforespring.yourfood.batch.rawrestaurant.RawRestaurantRepository;
import beforespring.yourfood.batch.rawrestaurant.fetch.RawRestaurantPagingItemReader;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawRestaurantPagingItemReaderFactory {
    private final OpenApiManagerFactory openApiManagerFactory;
    private final RawRestaurantRepository rawRestaurantRepository;

    public RawRestaurantPagingItemReader create(CuisineType cuisineType, int pageSize) {
        return new RawRestaurantPagingItemReader(
            new GyeonggiFetcherImpl(openApiManagerFactory, cuisineType),
            rawRestaurantRepository,
            pageSize
        );
    }

}
