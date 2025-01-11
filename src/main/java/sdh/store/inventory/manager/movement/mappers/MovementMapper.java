package sdh.store.inventory.manager.movement.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.movement.dto.CurrentStockDTO;
import sdh.store.inventory.manager.movement.dto.MovementCreateDTO;
import sdh.store.inventory.manager.movement.dto.MovementDTO;

import java.util.List;
import java.util.function.Supplier;

@Mapper
public interface MovementMapper {

    Integer createMovement(
            @Param("movement") MovementCreateDTO movementDTO
    );

    List<MovementDTO> findAllMovements();

    List<MovementDTO> findMovementsByProductId(
            @Param("productId") Long productId);

    List<MovementDTO> findMovementsByReferenceDocument(
            @Param("referenceDocument") String referenceDocument);

    CurrentStockDTO getCurrentStockByProductId(
            @Param("productId") Long productId);
}
