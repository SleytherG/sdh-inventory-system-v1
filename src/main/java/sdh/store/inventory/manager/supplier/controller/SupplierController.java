package sdh.store.inventory.manager.supplier.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.supplier.dto.SupplierCreateResponseDTO;
import sdh.store.inventory.manager.supplier.dto.SupplierDTO;
import sdh.store.inventory.manager.supplier.dto.SupplierDeleteResponseDTO;
import sdh.store.inventory.manager.supplier.dto.SupplierUpdateResponseDTO;
import sdh.store.inventory.manager.supplier.service.SupplierService;
import sdh.store.inventory.manager.util.ListPageableDTO;

import static sdh.store.inventory.manager.util.Constants.SUPPLIER_CREATED_SUCCESSFULLY;
import static sdh.store.inventory.manager.util.Constants.SUPPLIER_DELETED_SUCCESSFULLY;
import static sdh.store.inventory.manager.util.Constants.SUPPLIER_UPDATED_SUCCESSFULLY;

@RestController
@Tag(name = "Supplier")
@RequestMapping
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/suppliers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all suppliers", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SupplierDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Mono<ListPageableDTO<SupplierDTO>> findAllSuppliers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return supplierService.findAllSuppliers(page, size)
                .map(ListPageableDTO::new);
    }

    @PostMapping(value = "/suppliers",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a supplier", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SupplierDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<SupplierCreateResponseDTO> createSupplier(
            @Parameter(required = true) @RequestBody SupplierDTO supplier) {
        return supplierService.createSupplier(supplier)
                .map(code -> SupplierCreateResponseDTO
                        .builder()
                        .code(code)
                        .message(SUPPLIER_CREATED_SUCCESSFULLY)
                        .build());
    }

    @GetMapping(value = "/suppliers/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a supplier by ID", method = "GET", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SupplierDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<SupplierDTO> getSupplierById(
            @Parameter(required = true) @PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping(value = "/suppliers/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update a supplier by ID", method = "PUT", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SupplierDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<SupplierUpdateResponseDTO> updateSupplier(
            @Parameter(required = true) @PathVariable Long id,
            @Parameter(required = true) @RequestBody SupplierDTO supplier) {
        return supplierService.updateSupplier(id, supplier)
                .map(code -> SupplierUpdateResponseDTO
                        .builder()
                        .code(code)
                        .message(SUPPLIER_UPDATED_SUCCESSFULLY)
                        .build());
    }

    @DeleteMapping(value = "/suppliers/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a supplier by ID", method = "DELETE", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SupplierDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<SupplierDeleteResponseDTO> deleteSupplier(
            @Parameter(required = true) @PathVariable Long id) {
        return supplierService.deleteSupplier(id)
                .map(code -> SupplierDeleteResponseDTO
                        .builder()
                        .code(code)
                        .message(SUPPLIER_DELETED_SUCCESSFULLY)
                        .build());
    }
}
