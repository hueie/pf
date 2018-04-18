package com.polarisfinder.animals.service;

import java.util.List;

import com.polarisfinder.animals.entity.Animals;

public interface AnimalsService {
	boolean createAnimals(Animals Animals);
	List<Animals> getAnimalsById(int id, int paging);
	boolean deleteAnimals(Animals Animals);
	
	List<Animals> getAnimals(int id, int paging);
	void updateAnimals(int id, boolean Animals);
	Animals viewAnimals(int id) ;
	List<Animals> getRandomAnimals();
}
