package sdh.store.inventory.manager.movement.service;

import com.github.pagehelper.PageInfo;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.movement.dto.CurrentStockDTO;
import sdh.store.inventory.manager.movement.dto.MovementCreateDTO;
import sdh.store.inventory.manager.movement.dto.MovementDTO;

import java.util.List;

public interface MovementService {

    Mono<PageInfo<MovementDTO>> findAllMovements(int page, int size);

    Mono<Integer> createMovement(MovementCreateDTO movement);

    Mono<List<MovementDTO>> findMovementsByProductId(Long productId);

    Mono<List<MovementDTO>> findMovementsByReferenceDocument(String referenceDocument);

    Mono<CurrentStockDTO> getCurrentStockByProductId(Long productId);
}
