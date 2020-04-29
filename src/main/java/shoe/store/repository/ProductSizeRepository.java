package shoe.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import shoe.store.entity.ProductSize;

@Repository
public interface ProductSizeRepository extends BaseRepository<ProductSize, Long> {

	List<ProductSize> findByProductId(Long id);
}
