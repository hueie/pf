package com.polarisfinder.common.util;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * ========================================================
 * 주서비스 : kams
 * 서브시스템 : kams
 * 프로그램명 : Excel 관련 Util
 * 설명 :
 * 작성자 : @author Jung mi kyoung
 * 작성일 : 2014. 11. 10.
 * ========================================================
 * 수정자/수정일 :
 * 수정사유/내역 :
 * ========================================================
 */
public class ExcelUtil {

	
	
	/**
	 * Cell Style - header 와 body 분리
	 * @param workbook
	 * @param isHeader
	 * @return
	 */
	public static CellStyle getCellStyle(Workbook workbook, boolean isHeader){
		
		CellStyle style = workbook.createCellStyle();
		//header  인경우 컬러와 폰트 지정
		if(isHeader){
			style = getColorCellStyle(style);
			style = getFontCellStyle(style, workbook);
		}
		style = getLineCellStyle(style);
		return style;
	}
	
	
	
	
	
	
	/**
	 * Style 라인 지정
	 * @param style
	 * @return
	 */
	public static CellStyle getLineCellStyle(CellStyle style){

		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setWrapText(true); 
		
		return style;
	}
	
	
	
	
	
	/**
	 * Style Cell 컬러 지정 -  default YELLOW
	 * @param style
	 * @return
	 */
	public static CellStyle getColorCellStyle(CellStyle style){
		
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);// not BackgroundColor
		style.setFillForegroundColor(IndexedColors.YELLOW.index);
		style.setAlignment (HorizontalAlignment.CENTER);
		style.setVerticalAlignment (VerticalAlignment.CENTER);
		style.setWrapText(true); 
		
		return style;
	}
	


	
	
	/**
	 * Style  Cell  폰트 지정 
	 * @param style
	 * @param workbook
	 * @return
	 */
	public static CellStyle getFontCellStyle(CellStyle style, Workbook workbook){
		
		Font font = workbook.createFont();
		font.setBold(true);
		
		//제목 스타일에 폰트 적용, 정렬
		style.setFont(font);
		
		return style;
	}
	
}
