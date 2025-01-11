package sdh.store.inventory.manager.proxy.client;

import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sdh.store.inventory.manager.proxy.model.Product;

public interface OpenFoodFactsApi {

    @GET("api/v3/product/{barcode}.json")
    Mono<Product> getProductByBarcode(@Path("barcode") String barcode);
}
