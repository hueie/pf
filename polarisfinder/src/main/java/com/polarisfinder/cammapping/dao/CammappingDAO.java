package com.polarisfinder.cammapping.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cammapping.entity.Cam;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;

public interface CammappingDAO {
	void createCammapping(Cammapping cammapping);
	void deleteCammappingByFileuploadId(int fileupload_id, int user_id);
    List<Cam> getAllCams();
	List<Cammapping> getLinesfsByFileuploadId(int fileupload_id, int user_id);
    void updateBooksfIdToCammapping(Cammapping cammapping);
}
