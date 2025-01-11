package sdh.store.inventory.manager.sales.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.sales.dto.SalesCreateDTO;
import sdh.store.inventory.manager.sales.dto.SalesCreateResponseDTO;
import sdh.store.inventory.manager.sales.dto.SalesDTO;
import sdh.store.inventory.manager.sales.service.SalesService;
import sdh.store.inventory.manager.util.ListPageableDTO;

import static sdh.store.inventory.manager.util.Constants.SALES_CREATED_SUCCESSFULLY;

@RestController
@Tag(name = "Sales")
@RequestMapping
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping(value = "/sales",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all sales", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Operation.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Mono<ListPageableDTO<SalesDTO>> findAllSales(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return salesService.findAllSales(page, size)
                .map(ListPageableDTO::new);
    }

    @PostMapping(value = "/sales",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a sale", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SalesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<SalesCreateResponseDTO> createSales(
            @Parameter(required = true) @RequestBody SalesCreateDTO salesCreateDTO) {
        return salesService.createSale(salesCreateDTO)
                .map( code -> SalesCreateResponseDTO
                        .builder()
                        .code(code)
                        .message(SALES_CREATED_SUCCESSFULLY)
                        .build());
    }


}
