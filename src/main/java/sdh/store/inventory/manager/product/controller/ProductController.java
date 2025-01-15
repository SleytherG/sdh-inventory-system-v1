package sdh.store.inventory.manager.product.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sdh.store.inventory.manager.product.dto.*;
import sdh.store.inventory.manager.product.service.ProductService;
import sdh.store.inventory.manager.product.dto.ListPageableProductDTO;

import static sdh.store.inventory.manager.util.Constants.*;


@RestController
@Tag(name = "Product")
@RequestMapping
//@PreAuthorize("denyAll()")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products",
        produces = { MediaType.APPLICATION_JSON_VALUE })
//    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products", method = "GET",
        responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Operation.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Flowable<ListPageableProductDTO> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.findAllProducts(page, size)
                .map(ListPageableProductDTO::new);
    }

    @PostMapping(value = "/products",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
//    @PreAuthorize("hasAnyAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a product", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ProductCreateResponseDTO> createProduct(
            @Parameter(required = true) @RequestBody ProductCreateDTO product) {
        return productService.createProduct(product)
                .map(code ->
                        ProductCreateResponseDTO
                                .builder()
                                .code(code)
                                .message(PRODUCT_CREATED_SUCCESSFULLY)
                                .build());
    }

    @GetMapping(value = "/products/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
//    @PreAuthorize("hasAnyAuthority('CREATE')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a product by ID", method = "GET", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ProductDTO> getProductById(
            @Parameter(required = true) @PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(value = "/products/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update a product by ID", method = "PUT", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ProductUpdateResponseDTO> updateProduct(
            @Parameter(required = true) @PathVariable Long id,
            @Parameter(required = true) @RequestBody ProductUpdateDTO product) {
        return productService.updateProduct(id, product)
                .map(code -> ProductUpdateResponseDTO
                        .builder()
                        .code(code)
                        .message(PRODUCT_UPDATED_SUCCESSFULLY)
                        .build());
    }

    @DeleteMapping(value = "/products/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a product by ID", method = "DELETE", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ProductDeleteResponseDTO> deleteProduct(
            @Parameter(required = true) @PathVariable Long id) {
        return productService.deleteProduct(id)
                .map(code -> ProductDeleteResponseDTO
                        .builder()
                        .code(code)
                        .message(PRODUCT_DELETED_SUCCESSFULLY)
                        .build());
    }




}
