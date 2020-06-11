package com.ibm.fsd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.models.Pages;


/**
 * user Info
 */
@Repository
public interface BuyerProductRepository extends JpaRepository<ProductEntity, String> {
	
	ProductEntity findByCode(String code);
	
	@Query(value = "select * from product where name like CONCAT('%',:#{#pages.name},'%') "
			+ " or (category_code = :#{#pages.type}) "
			+ " or (manufacturer_code = :#{#pages.manu}) "
			+ " or (price between :#{#pages.startPrice} and :#{#pages.endPrice}) "
			+ " order by code", nativeQuery = true)
	Page<ProductEntity> findAllList(@Param("pages") Pages pages, Pageable pageable);
	
	
}
