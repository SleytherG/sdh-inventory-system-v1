package sdh.store.inventory.manager.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.proxy.client.OpenFoodFactsApi;
import sdh.store.inventory.manager.proxy.model.Product;

@Service
public class ProductService {

    @Autowired
    private OpenFoodFactsApi openFoodFactsApi;

    public Mono<Product> getProductByBarcode(String barcode) {
        return openFoodFactsApi.getProductByBarcode(barcode);
    }


}
