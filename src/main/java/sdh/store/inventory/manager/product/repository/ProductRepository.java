package sdh.store.inventory.manager.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdh.store.inventory.manager.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
