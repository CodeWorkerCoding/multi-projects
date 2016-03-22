package com.shenmajr.boot.serviceimp.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenmajr.boot.domain.item.Item;
import com.shenmajr.boot.repo.item.ItemRepo;
import com.shenmajr.boot.service.item.ItemService;

@Service(value="itemService")
public class ItemServiceImp implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Override
	public Item addObject(Item entity) {
		return itemRepo.save(entity);
	}

	@Override
	public List<Item> addObjects(List<Item> entities) {
		return itemRepo.save(entities);
	}

	@Override
	public Item update(Item entity) {
		return itemRepo.saveAndFlush(entity);
	}

	@Override
	public void del(Integer id) {
		itemRepo.delete(id);
	}

	@Override
	public Item get(Integer id) {
		return itemRepo.getOne(id);
	}

	@Override
	public List<Item> getAll() {
		return itemRepo.findAll();
	}

}
