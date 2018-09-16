package com.shop.service;

import java.util.List;

import com.shop.domain.Item;

public interface ItemService {
	List<Item> findAll();
	
	Item findOne(Long id);
	
	Item save(Item item);
	
	List<Item> blurrySearch(String title);
	
	void removeOne(Long id);
}
