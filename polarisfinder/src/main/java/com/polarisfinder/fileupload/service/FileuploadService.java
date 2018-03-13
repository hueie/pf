package com.polarisfinder.fileupload.service;

import java.util.List;

import com.polarisfinder.fileupload.entity.Fileupload;

public interface FileuploadService {

    boolean createFileupload(Fileupload fileupload);
    List<Fileupload> getFilesByFileuploadRegId(int fileupload_reg_id);
}
