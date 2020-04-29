package shoe.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoe.store.entity.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

	@Override
	@Query("FROM Product p where p.isDelete = 0 and (p.id = :id or p.name like %:text%)")
	Page<Product> search(@Param("id") long id, @Param("text") String name, Pageable pageable);

}
