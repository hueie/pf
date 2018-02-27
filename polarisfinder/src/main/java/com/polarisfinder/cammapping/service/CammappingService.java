package com.polarisfinder.cammapping.service;

import java.util.List;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cammapping.entity.Cam;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

public interface CammappingService {

    boolean createCammapping(Cammapping cammapping);
    boolean deleteCammappingByFileuploadId(int fileupload_id, int user_id);
    List<Cam> getAllCams();
    List<Cammapping> getLinesfsByFileuploadId(int fileupload_id, int user_id);
    void updateBooksfIdToCammapping(Cammapping cammapping);
}
