package com.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shop.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	
	List<Item> findByTitleContaining(String keyword);
	

}
