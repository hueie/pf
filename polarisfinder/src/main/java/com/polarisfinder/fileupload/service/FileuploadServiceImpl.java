package com.polarisfinder.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.fileupload.dao.FileuploadDAO;
import com.polarisfinder.fileupload.entity.Fileupload;

@Service
public class FileuploadServiceImpl implements FileuploadService {
	@Autowired
	private FileuploadDAO fileuploadDAO;
	
	@Override
	public synchronized boolean createFileupload(Fileupload fileupload){
		fileuploadDAO.createFileupload(fileupload);
		return true;
	}

	@Override
	public List<Fileupload> getFilesByFileuploadRegId(int fileupload_reg_id) {
		return fileuploadDAO.getFilesByFileuploadRegId(fileupload_reg_id);
	}
}
