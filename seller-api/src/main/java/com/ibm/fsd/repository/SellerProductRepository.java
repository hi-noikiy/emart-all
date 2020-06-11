package com.ibm.fsd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.ProductEntity;


/**
 * user Info
 */
@Repository
public interface SellerProductRepository extends JpaRepository<ProductEntity, String> {
	
	Page<ProductEntity> findByCodeOrCategoryCodeOrderByCodeAsc(String code, String categoryCode, Pageable pageable);
	
	ProductEntity findByCode(String code);
	
	@Modifying
	@Query(value = "update product set "
			+ "name = :#{#entity.name}, "
			+ "description = :#{#entity.description}, "
			+ "price = :#{#entity.price}, "
			+ "stock = :#{#entity.stock}, "
			+ "discount = :#{#entity.discount}, "
			+ "active = :#{#entity.active}, "
			+ "category_code = :#{#entity.categoryCode}, "
			+ "manufacturer_code = :#{#entity.manufacturerCode}, "
			+ "image_url = :#{#entity.imageUrl} "
			+ " where code=:#{#entity.code}", nativeQuery = true)
	int update(@Param("entity") ProductEntity entity);
	
}
