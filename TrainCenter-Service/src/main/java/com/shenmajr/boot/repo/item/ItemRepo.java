package com.shenmajr.boot.repo.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shenmajr.boot.domain.item.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
