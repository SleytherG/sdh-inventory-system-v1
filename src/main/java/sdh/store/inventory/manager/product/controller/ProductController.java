package sdh.store.inventory.manager.product.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.service.ProductService;

@RestController
@Tag(name = "Operation")
@RequestMapping
@Slf4j
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product",
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products", method = "GET",
        responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Operation.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Flowable<ResponseEntity<ProductDTO>> findAllProducts() {
        return productService.findAllProducts()
                .map(ResponseEntity::ok);
    }
}
