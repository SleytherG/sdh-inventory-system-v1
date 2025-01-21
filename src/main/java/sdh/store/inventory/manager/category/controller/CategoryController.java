package sdh.store.inventory.manager.category.controller;

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
import sdh.store.inventory.manager.category.dto.CategoryCreateDTO;
import sdh.store.inventory.manager.category.dto.CategoryCreateResponseDTO;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.category.dto.CategoryDeleteResponseDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateResponseDTO;
import sdh.store.inventory.manager.category.service.CategoryService;
import sdh.store.inventory.manager.util.ListPageableDTO;

import static sdh.store.inventory.manager.util.Constants.CATEGORY_CREATED_SUCCESSFULLY;
import static sdh.store.inventory.manager.util.Constants.CATEGORY_DELETED_SUCCESSFULLY;
import static sdh.store.inventory.manager.util.Constants.CATEGORY_UPDATED_SUCCESSFULLY;

@RestController
@Tag(name = "Category")
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all categories", method = "GET",
            responses = {
                @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = Operation.class))}),
                @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = Error.class))})})
    public Mono<ListPageableDTO<CategoryDTO>> findAllCategories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryService.findAllCategories(page, size)
                .map(ListPageableDTO::new);
    }

    @PostMapping(value = "/categories",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a category", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<CategoryCreateResponseDTO> createCategory(
            @Parameter(required = true) @RequestBody CategoryCreateDTO category) {
        return categoryService.createCategory(category)
                .map(code ->
                        CategoryCreateResponseDTO
                                .builder()
                                .code(code)
                                .message(CATEGORY_CREATED_SUCCESSFULLY)
                                .build());
    }

    @GetMapping(value = "/categories/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a category by ID", method = "GET", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<CategoryDTO> getCategoryById(
            @Parameter(required = true) @PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping(value = "/categories/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update a category by ID", method = "PUT", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDTO.class))}),
    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
    schema = @Schema(implementation = Error.class))})})
    public Mono<CategoryUpdateResponseDTO> updateCategory(
            @Parameter(required = true) @PathVariable Long id,
            @Parameter(required = true) @RequestBody CategoryUpdateDTO category) {
        return categoryService.updateCategory(id, category)
                .map(code -> CategoryUpdateResponseDTO
                        .builder()
                        .code(code)
                        .message(CATEGORY_UPDATED_SUCCESSFULLY)
                        .build());
    }

    @DeleteMapping(value = "/categories/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a category by ID", method = "DELETE", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Mono<CategoryDeleteResponseDTO> deleteCategory(
            @Parameter(required = true) @PathVariable Long id) {
        return categoryService.deleteCategory(id)
                .map(code -> CategoryDeleteResponseDTO
                        .builder()
                        .code(code)
                        .message(CATEGORY_DELETED_SUCCESSFULLY)
                        .build());
    }



}
