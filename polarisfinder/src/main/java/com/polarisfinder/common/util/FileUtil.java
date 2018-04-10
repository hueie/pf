package com.polarisfinder.common.util;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * 파일관련 유틸
 * @author Yunjung
 *
 */
public class FileUtil {

	@Value("${polarisfinder.file.upload.dir}")
	private static String polarisfinder_FILE_UPLOAD_DIR;

	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	public static final String SEPARATOR = "/";
	
	/**
	 * 파일 삭제
	 * @param fullpath
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFile(String fullpath) throws Exception {
		try {
			File file = new File(fullpath);
			return file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 파일 업로드
	 * @param file
	 * @param relativePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file, String relativePath, String fileName) throws Exception {
		
		String filePath = polarisfinder_FILE_UPLOAD_DIR + relativePath;
		String fullPath = filePath + FileUtil.SEPARATOR + fileName;
		
		//디렉토리 생성
		File directory = new File(filePath);
		directory.mkdirs();
		
		//기존파일이 있으면 삭제
		File oldFile = new File(fullPath);
		if (oldFile.exists()) {
			oldFile.delete();
		}
		
		//파일저장
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fullPath));
		
		return fullPath;
	}
	
	
	/**
	 * 썸네일 생성
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @param width
	 * @return
	 * @throws IOException
	 */
	public static boolean createThumbnail(String sourceFilePath, String targetFilePath, int width) throws IOException {
		File save = new File(targetFilePath.replaceAll("/", "\\" + File.separator));
		FileInputStream fis = new FileInputStream(sourceFilePath.replaceAll("/", "\\" + File.separator));
		BufferedImage im = ImageIO.read(fis);
		Image inImage = new ImageIcon(sourceFilePath).getImage();
		double scale = (double) width / (double) inImage.getHeight(null);
		if (inImage.getWidth(null) > inImage.getHeight(null)) {
			scale = (double) width / (double) inImage.getWidth(null);
		}
		int scaledW = (int) (scale * inImage.getWidth(null));
		int scaledH = (int) (scale * inImage.getHeight(null));
		BufferedImage thumb = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = thumb.createGraphics();
		g2.drawImage(im, 0, 0, scaledW, scaledH, null);
		return ImageIO.write(thumb, "jpg", save);
	}
	
	
	public static String createTifThumbnail(String sourceFile, String targetFile, int width) throws Exception {
		
		String fullPath = polarisfinder_FILE_UPLOAD_DIR + targetFile;
		
		File firDir = new File(fullPath);
		if(!firDir.exists()){
			firDir.mkdirs();
		}

		BufferedImage im = null;
		RenderedOp op = JAI.create("fileload", sourceFile);
        im = op.getAsBufferedImage();
        int orignWidth = im.getWidth();
		int orignHeight = im.getHeight();
		
		int WidthThumbSize = 300;
		int HeightThumbSize = 300;
		int imgWidth = 0;
		int imgHeight = 0;
		
		if(orignWidth >= orignHeight){ // 가로가 크면
			//원본사이즈가 썸네일사이즈보다 작은경우
			if (orignWidth <= width){
				imgWidth = orignWidth;
			}else{
				imgWidth = WidthThumbSize;
			}
			
			imgHeight = Math.round((orignHeight * imgWidth) / orignWidth);
			//세로 최대 썸네일 크기를 초과하는 경우 세로에 따라 가로 비율을 조정함
			if (HeightThumbSize < imgHeight){
				imgHeight = HeightThumbSize;
				imgWidth = Math.round((orignWidth * HeightThumbSize) / orignHeight);
			}
		}else if(orignHeight > orignWidth){
			if (orignHeight <= HeightThumbSize){
				imgHeight = orignHeight;
			}else{
				imgHeight = HeightThumbSize;
			}
			
			imgWidth = Math.round((orignWidth * imgHeight) / orignHeight);
			
			//가로 최대 썸네일 크기를 초과하는 경우 가로에 따라 세로 비율을 조정함
			if (WidthThumbSize < imgWidth){
				imgWidth = WidthThumbSize;
				imgHeight = Math.round((orignHeight * WidthThumbSize) / orignWidth);
			}
		}
		
		BufferedImage thumb = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		
        thumb.createGraphics().drawImage(im, 0, 0,imgWidth, imgHeight, null);
		// write to jpg file
		ImageIO.write(thumb, "jpg", new File(fullPath));
		
		return fullPath;
	}
	
	
	/**
	 * 파일경로 변환('\' -> '/')
	 * @param filePath
	 * @return
	 */
	public static String convertFileSeparator(String filePath) {
		if (StringUtils.isNotEmpty(filePath)) {
			return filePath.replace("\\", "/");
		}
		return filePath;
	}
	
	/**
	 * 파일 가로, 세로 이미지 알아내기
	 * @param filePath
	 * @return
	 */
	public static String isWidthHeight(String filePath) {
		String isWidthHeight ="";
		String ext = filePath.substring(filePath.lastIndexOf( "." )+1).toUpperCase();
		if (StringUtils.isNotEmpty(filePath)) {
			  BufferedImage bi;
			try {
				if (ext.equals("TIF") || ext.equals("TIFF")){
					RenderedOp  render = JAI.create("fileload", filePath);//원본 이미지에 대한 RenderedOp 객체 생성
					bi = render.getAsBufferedImage();//BufferImage 객체를 얻어옴 TIF인 경우만 해당!!!
					int width = bi.getWidth();
					int height = bi.getHeight();
					
					if(width > height) {
						isWidthHeight = "width";
					} else if(width == height) {
						isWidthHeight = "same";
					} else {
						isWidthHeight = "height";
					}
				}else{
					bi = ImageIO.read(new File(filePath));
					
					int width = bi.getWidth();
					int height = bi.getHeight();
					
					if(width > height) {
						isWidthHeight = "width";
					} else if(width == height) {
						isWidthHeight = "same";
					} else {
						isWidthHeight = "height";
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return isWidthHeight;
		}
		return isWidthHeight;
	}
	
	public static Boolean isCmykCheck(String filePath) {
		
		Boolean result = true;
		File isFile = new File(filePath);
		
		try {
			
			ImageInputStream stream = ImageIO.createImageInputStream(isFile);
	        Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
	        
	        while (iter.hasNext()) {
	            ImageReader reader = iter.next();
	            reader.setInput(stream);
	 
	            BufferedImage image;
	            ICC_Profile profile = null;
	            try {
	                image = reader.read(0);
	                result = true;
	            } catch (IIOException e) {
	                result = false;
	            } finally {
	                stream.close(); //원본에는 close해주는 부분이 없어서 추가해 줬음, 위치상으로 애매한데 리턴 전이라 그냥 닫아줌
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	 
}
