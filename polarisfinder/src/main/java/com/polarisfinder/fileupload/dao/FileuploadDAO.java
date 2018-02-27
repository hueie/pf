package com.polarisfinder.fileupload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polarisfinder.fileupload.entity.Fileupload;

public interface FileuploadDAO {
	void createFileupload(Fileupload fileupload);
	List<Fileupload> getFilesByFileuploadRegId(int fileupload_reg_id);
}
