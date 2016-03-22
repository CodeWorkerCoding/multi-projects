package com.shenmajr.boot.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
	
	T addObject(T entity);
	
	List<T> addObjects(List<T> entities);
	
	T update(T entity);
	
	void del(ID id);
	
	T get(ID id);
	
	List<T> getAll();

}
