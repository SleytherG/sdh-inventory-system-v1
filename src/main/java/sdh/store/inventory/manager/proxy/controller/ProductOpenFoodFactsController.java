package sdh.store.inventory.manager.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.proxy.model.Product;
import sdh.store.inventory.manager.proxy.service.ProductService;

@RestController
@RequestMapping()
public class ProductOpenFoodFactsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getOpenFoodFactProduct/{barcode}")
    public Mono<Product> getOpenFoodFactProduct(@PathVariable String barcode) {
        return productService.getProductByBarcode(barcode);
    }
}
