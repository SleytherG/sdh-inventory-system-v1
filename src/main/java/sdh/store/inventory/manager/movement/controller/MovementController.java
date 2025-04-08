package sdh.store.inventory.manager.movement.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.movement.dto.MovementCreateDTO;
import sdh.store.inventory.manager.movement.dto.MovementCreateResponseDTO;
import sdh.store.inventory.manager.movement.dto.MovementDTO;
import sdh.store.inventory.manager.movement.service.MovementService;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.service.ProductService;
import sdh.store.inventory.manager.util.ListPageableDTO;

import java.util.List;

import static sdh.store.inventory.manager.util.Constants.MOVEMENT_CREATED_SUCCESSFULLY;

@RestController
@Tag(name = "Movement")
@RequestMapping
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping(value = "/movements",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all movements", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MovementDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Mono<ListPageableDTO<MovementDTO>> findAllMovements(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return movementService.findAllMovements(page, size)
                .map(ListPageableDTO::new);
    }

    @PostMapping(value = "/movements",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a movement", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<MovementCreateResponseDTO> createMovement(
            @Parameter(required = true) @RequestBody MovementCreateDTO movement) {
        return movementService.createMovement(movement)
                .map( code ->
                        MovementCreateResponseDTO
                                .builder()
                                .code(code)
                                .message(MOVEMENT_CREATED_SUCCESSFULLY)
                                .build());
    }

    @GetMapping(value = "/movements/byProductId/{productId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all movements by productId", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MovementDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Mono<List<MovementDTO>> findMovementsByProductId(
            @Parameter(required = true) @PathVariable("productId") Long productId) {
        return movementService.findMovementsByProductId(productId);
    }

    @GetMapping(value = "/movements/byReferenceDocument/{referenceDocument}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all movements by referenceDocument", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MovementDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Mono<List<MovementDTO>> findMovementsByReferenceDocument(
            @Parameter(required = true) @PathVariable("referenceDocument") String referenceDocument) {
        return movementService.findMovementsByReferenceDocument(referenceDocument);
    }


}
