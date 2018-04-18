package com.polarisfinder.animals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.animals.dao.AnimalsDAO;
import com.polarisfinder.animals.entity.Animals;
@Service
public class AnimalsServiceImpl implements AnimalsService {

	@Autowired
	private AnimalsDAO AnimalsDAO;
	
	@Override
	public synchronized boolean createAnimals(Animals Animals){
		AnimalsDAO.createAnimals(Animals);
    	return true;
	}

	@Override
	public List<Animals> getAnimalsById(int id, int paging) {
		return AnimalsDAO.getAnimalsById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteAnimals(Animals Animals){
		AnimalsDAO.deleteAnimals(Animals);
    	return true;
	}

	@Override
	public List<Animals> getAnimals(int id, int paging) {
		return AnimalsDAO.getAnimals(id, paging);
	}
	@Override
	public void updateAnimals(int id, boolean Animals) {
		AnimalsDAO.updateAnimals(id, Animals);
	}
	@Override
	public Animals viewAnimals(int id) {
		return AnimalsDAO.viewAnimals(id);
	}
	
	@Override
	public List<Animals> getRandomAnimals(){
		return AnimalsDAO.getRandomAnimals();
	}
}
