package com.commerce.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commerce.stock.entity.SoldItem;


public interface SoldItemRepository extends CrudRepository<SoldItem, Long>{
	
	
	@Query(nativeQuery = true, value = "select product_Id, sum(quantity) amount from Sold_Item where timestamp between ?1 and ?2 group by product_Id order by 2 desc limit 3")
	List<Object[]> top3SoldProducts(Date from, Date to);

}
