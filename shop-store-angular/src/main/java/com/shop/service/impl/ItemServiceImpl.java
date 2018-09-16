package com.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.domain.Item;
import com.shop.repository.ItemRepository;
import com.shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired ItemRepository itemRepository;
	
	@Override
	public List<Item> findAll() {
		
		List<Item> itemList = (List<Item>)itemRepository.findAll();
		
		List<Item> activeItemList = new ArrayList<>();
		
		for(Item item : itemList) {
			if(item.isActive()) {
				activeItemList.add(item);
			}
		}
		
		return activeItemList;
	}

	@Override
	public Item findOne(Long id) {
		// TODO Auto-generated method stub
		return itemRepository.findOne(id);
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public List<Item> blurrySearch(String keyword) {
		
		List<Item> itemList = itemRepository.findByTitleContaining(keyword);
		
		List<Item> activeItemList = new ArrayList<>();
		
		for(Item item : itemList) {
			if(item.isActive()) {
				activeItemList.add(item);
			}
		}
		
		return activeItemList;
	} 

	@Override
	public void removeOne(Long id) {
		itemRepository.delete(id);
	}

}
