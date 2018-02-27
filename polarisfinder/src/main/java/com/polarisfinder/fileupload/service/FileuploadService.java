package com.polarisfinder.fileupload.service;

import com.polarisfinder.fileupload.entity.Fileupload;

import java.util.List;

import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cubemap.entity.Cubemap;

public interface FileuploadService {

    boolean createFileupload(Fileupload fileupload);
    List<Fileupload> getFilesByFileuploadRegId(int fileupload_reg_id);
}
