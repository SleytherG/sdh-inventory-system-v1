package sdh.store.inventory.manager.movement.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.movement.dto.CurrentStockDTO;
import sdh.store.inventory.manager.movement.dto.MovementCreateDTO;
import sdh.store.inventory.manager.movement.dto.MovementDTO;
import sdh.store.inventory.manager.movement.mappers.MovementMapper;
import sdh.store.inventory.manager.movement.service.MovementService;
import sdh.store.inventory.manager.util.PaginationUtils;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    private final MovementMapper movementMapper;

    @Autowired
    public MovementServiceImpl(MovementMapper movementMapper) {
        this.movementMapper = movementMapper;
    }

    @Override
    public Mono<PageInfo<MovementDTO>> findAllMovements(int page, int size) {
        return Mono.fromCallable(() -> PaginationUtils.toPageInfo(page, size, movementMapper::findAllMovements))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Integer> createMovement(MovementCreateDTO movement) {
        return Mono.fromCallable(() -> movementMapper.createMovement(movement))
                .doOnError(RuntimeException::new)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<MovementDTO>> findMovementsByProductId(Long productId) {
        return Mono.fromCallable(() -> movementMapper.findMovementsByProductId(productId))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<MovementDTO>> findMovementsByReferenceDocument(String referenceDocument) {
        return Mono.fromCallable(() -> movementMapper.findMovementsByReferenceDocument(referenceDocument))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CurrentStockDTO> getCurrentStockByProductId(Long productId) {
        return Mono.just(movementMapper.getCurrentStockByProductId(productId));
    }
}
